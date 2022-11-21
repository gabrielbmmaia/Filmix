package com.example.filmix.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.model.FilmDto
import com.example.filmix.data.model.FilmRemoteKeys
import com.example.filmix.data.remote.FilmService

@OptIn(ExperimentalPagingApi::class)
class FilmRemoteMediator(
    private val filmService: FilmService,
    private val filmDatabase: FilmDatabase
) : RemoteMediator<Int, FilmDto>() {

    private val filmDao = filmDatabase.filmDao()
    private val filmRemoteKeysDao = filmDatabase.filmRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FilmDto>
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

            val response = filmService.getPopularFilms(page = currentPage)
            val endOfPaginationReached = response.films.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            filmDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    filmDao.deleteAllFilms()
                    filmRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.films.map { filmDto ->
                    FilmRemoteKeys(
                        id = filmDto.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                filmRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                filmDao.addFilms(films = response.films)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.e("RemoteMediator", e.toString())
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, FilmDto>
    ): FilmRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                filmRemoteKeysDao.getRemoteKey(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, FilmDto>
    ): FilmRemoteKeys? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { filmDto -> filmRemoteKeysDao.getRemoteKey(id = filmDto.id) }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, FilmDto>,
    ): FilmRemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { filmDto -> filmRemoteKeysDao.getRemoteKey(id = filmDto.id) }
    }
}
