package com.example.filmix.domain.repository

import com.example.filmix.domain.model.film.FilmDetails
import com.example.filmix.domain.model.serie.SerieDetails

interface TrendingRepository {

    suspend fun getTrendingFilm(): FilmDetails

    suspend fun getTrendingSerie(): SerieDetails

}