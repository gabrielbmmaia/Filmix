package com.example.filmix.domain.repository

import com.example.filmix.domain.model.TrendingFilm

interface TrendingRepository {

    suspend fun getTrendingFilm(): TrendingFilm

}