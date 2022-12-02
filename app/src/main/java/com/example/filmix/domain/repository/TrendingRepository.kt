package com.example.filmix.domain.repository

import com.example.filmix.domain.model.FilmDetails

interface TrendingRepository {

    suspend fun getTrendingFilm(): FilmDetails

}