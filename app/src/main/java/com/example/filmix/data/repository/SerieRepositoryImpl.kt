package com.example.filmix.data.repository

import androidx.paging.*
import com.example.filmix.core.Constants.PAGE_SIZE
import com.example.filmix.data.local.SerieDatabase
import com.example.filmix.data.paging.SerieRemoteMediator
import com.example.filmix.data.remote.SerieService
import com.example.filmix.domain.model.serie.Serie
import com.example.filmix.domain.repository.SerieRepository
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
}