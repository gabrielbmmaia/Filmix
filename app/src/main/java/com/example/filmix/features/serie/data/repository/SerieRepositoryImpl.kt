package com.example.filmix.features.serie.data.repository

import androidx.paging.*
import com.example.filmix.core.Constants.PAGE_SIZE
import com.example.filmix.features.serie.data.paging.PopularSeriePagingSource
import com.example.filmix.features.serie.data.paging.RatedSeriePagingSource
import com.example.filmix.features.serie.data.remote.SerieService
import com.example.filmix.features.serie.domain.model.SerieDetails
import com.example.filmix.features.serie.domain.repository.SerieRepository
import com.example.filmix.features.shared.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class SerieRepositoryImpl @Inject constructor(
    private val serieService: SerieService
) : SerieRepository {

    override fun getPopularSeries(): Flow<PagingData<Media>> {
        val pagingSourceFactory = { PopularSeriePagingSource(serieService = serieService) }

        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toMedia() }
        }
    }

    override fun getTopRatedSeries(): Flow<PagingData<Media>> {
        val pagingSourceFactory = { RatedSeriePagingSource(serieService = serieService) }

        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toMedia() }
        }
    }

    override suspend fun getSerieDetails(serieId: String): SerieDetails {
        val serieDetailsDto = serieService.getSerieDetails(serieId = serieId.toInt())
        return serieDetailsDto.toSerieDetails()
    }
}