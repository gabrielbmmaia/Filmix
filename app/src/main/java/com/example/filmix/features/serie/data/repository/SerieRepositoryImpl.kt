package com.example.filmix.features.serie.data.repository

import androidx.paging.*
import com.example.filmix.core.Constants.PAGE_SIZE
import com.example.filmix.features.serie.data.local.SerieDatabase
import com.example.filmix.features.serie.data.paging.SerieRemoteMediator
import com.example.filmix.features.serie.data.remote.SerieService
import com.example.filmix.features.serie.domain.model.Serie
import com.example.filmix.features.serie.domain.model.SerieDetails
import com.example.filmix.features.serie.domain.repository.SerieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class SerieRepositoryImpl @Inject constructor(
    private val serieService: SerieService,
    private val serieDatabase: SerieDatabase
) : SerieRepository {

    override fun getPopularSerie(): Flow<PagingData<Serie>> {
        val pagingSourceFactory = { serieDatabase.serieDao().getAllSeries() }

        val page = Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ), remoteMediator = SerieRemoteMediator(
                serieService = serieService,
                serieDatabase = serieDatabase
            ), pagingSourceFactory = pagingSourceFactory
        ).flow

        return page.map { pagingData ->
            pagingData.map { it.toSerie() }
        }
    }

    override suspend fun getSerieDetails(serieId: String): SerieDetails {
        val serieDetailsDto = serieService.getSerieDetails(serieId = serieId.toInt())
        return serieDetailsDto.toSerieDetails()
    }
}