package com.example.filmix.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.filmix.core.Constants.FILM_PAGE_SIZE
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.model.FilmDto
import com.example.filmix.data.paging.FilmRemoteMediator
import com.example.filmix.data.remote.FilmService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@ExperimentalPagingApi
class FilmRepositoryImpl @Inject constructor(
    private val filmService: FilmService,
    private val filmDatabase: FilmDatabase
) {

    fun getAllFilms(): Flow<PagingData<FilmDto>> {
        val pagingSourceFactory = { filmDatabase.filmDao().getAllFilms() }
        return flow {
            Pager(
                config = PagingConfig(pageSize = FILM_PAGE_SIZE),
                remoteMediator = FilmRemoteMediator(
                    filmService = filmService,
                    filmDatabase = filmDatabase
                ),
                pagingSourceFactory = pagingSourceFactory
            )
        }
    }
}
