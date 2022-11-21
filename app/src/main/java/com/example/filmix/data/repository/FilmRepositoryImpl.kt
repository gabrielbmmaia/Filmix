package com.example.filmix.data.repository

import androidx.paging.*
import com.example.filmix.core.Constants.FILM_MAX_PAGE_SIZE
import com.example.filmix.core.Constants.FILM_PAGE_SIZE
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.paging.FilmRemoteMediator
import com.example.filmix.data.remote.FilmService
import com.example.filmix.domain.model.Film
import com.example.filmix.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ExperimentalPagingApi
class FilmRepositoryImpl @Inject constructor(
    private val filmService: FilmService,
    private val filmDatabase: FilmDatabase
) : FilmRepository {

    override fun getAllFilms(): Flow<PagingData<Film>> {
        val pagingSourceFactory = { filmDatabase.filmDao().getAllFilms() }

        val pager = Pager(
            config = PagingConfig(
                pageSize = FILM_PAGE_SIZE
            ),
            remoteMediator = FilmRemoteMediator(
                filmService = filmService,
                filmDatabase = filmDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toFilm() }
        }
    }
}
