package com.example.filmix.data.repository

import com.example.filmix.core.Constants.DAY_TIME_WINDOW
import com.example.filmix.core.Constants.FILM_MEDIA_TYPE
import com.example.filmix.core.Constants.SERIE_MEDIA_TYPE
import com.example.filmix.data.remote.FilmService
import com.example.filmix.data.remote.SerieService
import com.example.filmix.data.remote.TrendingService
import com.example.filmix.domain.model.film.FilmDetails
import com.example.filmix.domain.model.serie.SerieDetails
import com.example.filmix.domain.repository.TrendingRepository
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val trendingService: TrendingService,
    private val filmService: FilmService,
    private val serieService: SerieService
) : TrendingRepository {

    override suspend fun getTrendingFilm(): FilmDetails {
        val result = trendingService.getTrendingItem(
            mediaType = FILM_MEDIA_TYPE,
            timeWindow = DAY_TIME_WINDOW
        )
        val trendingFilmItem = result.results[0]

        val filmDetailsDto = filmService.filmDetails(trendingFilmItem.id)

        return filmDetailsDto.toFilmDetails()
    }

    override suspend fun getTrendingSerie(): SerieDetails {
        val result = trendingService.getTrendingItem(
            mediaType = SERIE_MEDIA_TYPE,
            timeWindow = DAY_TIME_WINDOW
        )
        val trendingSerieItem = result.results[0]

        val serieDetailsDto = serieService.getSerieDetails(trendingSerieItem.id)

        return serieDetailsDto.toSerieDetails()
    }
}