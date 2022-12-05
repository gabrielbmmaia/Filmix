package com.example.filmix.presentation.serie

import com.example.filmix.domain.model.serie.SerieDetails

sealed class TrendingSerieState {
    class Success(val data: SerieDetails) : TrendingSerieState()
    class Error(val message: String) : TrendingSerieState()
    object Loading : TrendingSerieState()
    object Empty : TrendingSerieState()
}