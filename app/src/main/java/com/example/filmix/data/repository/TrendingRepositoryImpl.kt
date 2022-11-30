package com.example.filmix.data.repository

import com.example.filmix.core.Constants.DAY_TIME_WINDOW
import com.example.filmix.core.Constants.FILM_MEDIA_TYPE
import com.example.filmix.data.remote.TrendingService
import com.example.filmix.domain.model.Film
import com.example.filmix.domain.repository.TrendingRepository
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val trendingService: TrendingService
) : TrendingRepository {

    override suspend fun getTrendingFilm(): Film {
        val filmResponse = trendingService.getTrendingItem(
            mediaType = FILM_MEDIA_TYPE,
            timeWindow = DAY_TIME_WINDOW
        )

        return filmResponse.films[0].toFilm()
    }
}