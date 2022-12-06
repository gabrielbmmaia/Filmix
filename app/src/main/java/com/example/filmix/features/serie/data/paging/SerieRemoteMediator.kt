package com.example.filmix.features.serie.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.filmix.core.Constants.REMOTEMEDIATOR_TAG
import com.example.filmix.features.serie.data.local.SerieDatabase
import com.example.filmix.features.serie.data.model.SerieDto
import com.example.filmix.features.serie.data.model.SerieRemoteKeys
import com.example.filmix.features.serie.data.remote.SerieService

@OptIn(ExperimentalPagingApi::class)
class SerieRemoteMediator(
    private val serieService: SerieService,
    private val serieDatabase: SerieDatabase
) : RemoteMediator<Int, SerieDto>() {

    private val serieDao = serieDatabase.serieDao()
    private val serieRemoteKeysDao = serieDatabase.serieRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, SerieDto>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {

                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = serieService.getPopularSeries(page = currentPage)
            val endOfPaginationReached = response.series.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            serieDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    serieDao.deleteAllSeries()
                    serieRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.series.map { serieDto ->
                    SerieRemoteKeys(
                        id = serieDto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                serieRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                serieDao.addSeries(series = response.series)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e(REMOTEMEDIATOR_TAG, e.toString())
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, SerieDto>
    ): SerieRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                serieRemoteKeysDao.getRemoteKey(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, SerieDto>
    ): SerieRemoteKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { serieDto -> serieRemoteKeysDao.getRemoteKey(id = serieDto.id) }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, SerieDto>,
    ): SerieRemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { serieDto -> serieRemoteKeysDao.getRemoteKey(id = serieDto.id) }
    }
}
