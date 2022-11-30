package com.example.filmix.presentation.home

import androidx.paging.PagingData
import com.example.filmix.domain.model.Film

sealed class TrendingState {
    class Success(val data: Film): TrendingState()
    class Error(val message: String): TrendingState()
    object Loading: TrendingState()
    object Empty: TrendingState()
}