package com.example.filmix.data.repository

import android.util.Log
import androidx.paging.*
import com.example.filmix.core.Constants.FILM_PAGE_SIZE
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.model.FilmDetailsDto
import com.example.filmix.data.paging.FilmRemoteMediator
import com.example.filmix.data.paging.SearchFilmPagingSource
import com.example.filmix.data.remote.TMDBService
import com.example.filmix.domain.model.Film
import com.example.filmix.domain.model.FilmDetails
import com.example.filmix.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject


@ExperimentalPagingApi
class FilmRepositoryImpl @Inject constructor(
    private val filmService: TMDBService,
    private val filmDatabase: FilmDatabase
) : FilmRepository {

    override fun getPopularFilms(): Flow<PagingData<Film>> {
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

    override fun getSearchedFilms(query: String): Flow<PagingData<Film>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = FILM_PAGE_SIZE
            ),
            pagingSourceFactory = {
                SearchFilmPagingSource(
                    searchService = filmService,
                    query = query
                )
            }
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toFilm() }
        }
    }

    override suspend fun getFilmDetails(filmId: String): Response<FilmDetailsDto> {
        return  filmService.filmDetails(filmId = filmId.toInt())
    }
}
