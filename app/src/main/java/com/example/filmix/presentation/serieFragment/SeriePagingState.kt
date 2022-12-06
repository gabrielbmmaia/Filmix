package com.example.filmix.presentation.serieFragment

import androidx.paging.PagingData
import com.example.filmix.features.serie.domain.model.Serie

sealed class SeriePagingState {
    class Success(val data: PagingData<Serie>) : SeriePagingState()
    class Error(val message: String) : SeriePagingState()
    object Loading : SeriePagingState()
    object Empty : SeriePagingState()
}