package com.example.filmix.features.filmList.data.repository

import androidx.paging.*
import com.example.filmix.core.Constants.PAGE_SIZE
import com.example.filmix.features.filmList.data.paging.PopularFilmPagingSource
import com.example.filmix.features.filmList.data.paging.RatedFilmPagingSource
import com.example.filmix.features.filmList.data.paging.SoonFilmPagingSource
import com.example.filmix.features.filmList.data.paging.TheatreFilmPagingSource
import com.example.filmix.features.filmList.data.remote.FilmService
import com.example.filmix.features.shared.domain.model.Media
import com.example.filmix.features.filmList.domain.model.FilmDetails
import com.example.filmix.features.filmList.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class FilmRepositoryImpl @Inject constructor(
    private val filmService: FilmService
) : FilmRepository {

    /**
     * Chamada da lista de Filmes mais Populares
     * */
    override fun getPopularFilms(): Flow<PagingData<Media>> {
        val pagingSourceFactory = { PopularFilmPagingSource(filmService = filmService) }
        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toMedia() }
        }
    }

    /**
     * Chamada da lista de Filmes mais bem avaliados
     * */
    override fun getTopRatedFilms(): Flow<PagingData<Media>> {
        val pagingSourceFactory = { RatedFilmPagingSource(filmService = filmService) }

        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toMedia() }
        }
    }

    /**
     * Chamada da lista de Filmes Em Breve
     * */
    override fun getSoonFilms(): Flow<PagingData<Media>> {
        val pagingSourceFactory = { SoonFilmPagingSource(filmService = filmService) }

        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toMedia() }
        }
    }

    /**
     * Chamada da lista de Filmes Em Cartaz
     * */
    override fun getTheatresFilms(): Flow<PagingData<Media>> {
        val pagingSourceFactory = { TheatreFilmPagingSource(filmService = filmService) }

        val pager = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = pagingSourceFactory
        ).flow

        return pager.map { pagingData ->
            pagingData.map { it.toMedia() }
        }
    }

    override suspend fun getFilmDetails(filmId: String): FilmDetails {
        val filmDetailsDto = filmService.filmDetails(filmId = filmId.toInt())
        return filmDetailsDto.toFilmDetails()
    }
}
