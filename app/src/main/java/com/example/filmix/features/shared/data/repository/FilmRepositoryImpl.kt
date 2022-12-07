package com.example.filmix.features.shared.data.repository

import androidx.paging.*
import com.example.filmix.core.Constants.PAGE_SIZE
import com.example.filmix.features.films.data.paging.PopularFilmPagingSource
import com.example.filmix.features.films.data.remote.FilmService
import com.example.filmix.features.films.domain.model.Film
import com.example.filmix.features.films.domain.model.FilmDetails
import com.example.filmix.features.shared.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class FilmRepositoryImpl @Inject constructor(
    private val filmService: FilmService
) : FilmRepository {

    override fun getPopularFilms(): Flow<PagingData<Film>> {
        val pagingSourceFactory = { PopularFilmPagingSource(filmService = filmService) }
        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toFilm() }
        }
    }

    override suspend fun getFilmDetails(filmId: String): FilmDetails {
        val filmDetailsDto = filmService.filmDetails(filmId = filmId.toInt())
        return filmDetailsDto.toFilmDetails()
    }
}
