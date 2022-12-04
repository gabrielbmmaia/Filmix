package com.example.filmix.domain.repository

import com.example.filmix.domain.model.film.FilmDetails

interface TrendingRepository {

    suspend fun getTrendingFilm(): FilmDetails

}