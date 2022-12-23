package com.example.filmix.features.trending.data.repository

import com.example.filmix.core.Constants.DAY_TIME_WINDOW
import com.example.filmix.core.Constants.FILM_MEDIA_TYPE
import com.example.filmix.core.Constants.SERIE_MEDIA_TYPE
import com.example.filmix.features.filmList.data.remote.FilmService
import com.example.filmix.features.filmList.domain.model.FilmDetails
import com.example.filmix.features.serie.data.remote.SerieService
import com.example.filmix.features.serie.domain.model.SerieDetails
import com.example.filmix.features.trending.data.remote.TrendingService
import com.example.filmix.features.trending.domain.repository.TrendingRepository
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
