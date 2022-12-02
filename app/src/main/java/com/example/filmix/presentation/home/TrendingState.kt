package com.example.filmix.presentation.home

import com.example.filmix.domain.model.TrendingFilm

sealed class TrendingState {
    class Success(val data: TrendingFilm) : TrendingState()
    class Error(val message: String) : TrendingState()
    object Loading : TrendingState()
    object Empty : TrendingState()
}