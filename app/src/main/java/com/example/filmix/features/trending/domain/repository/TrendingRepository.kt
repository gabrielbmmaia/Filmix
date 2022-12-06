package com.example.filmix.features.trending.domain.repository

import com.example.filmix.features.films.domain.model.FilmDetails
import com.example.filmix.features.serie.domain.model.SerieDetails

interface TrendingRepository {

    suspend fun getTrendingFilm(): FilmDetails

    suspend fun getTrendingSerie(): SerieDetails

}
