package com.example.filmix.domain.repository

import com.example.filmix.domain.model.Film

interface TrendingRepository {

    suspend fun getTrendingFilm(): Film

}