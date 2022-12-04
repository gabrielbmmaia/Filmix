package com.example.filmix.presentation.home

import com.example.filmix.domain.model.film.FilmDetails

sealed class TrendingState {
    class Success(val data: FilmDetails) : TrendingState()
    class Error(val message: String) : TrendingState()
    object Loading : TrendingState()
    object Empty : TrendingState()
}