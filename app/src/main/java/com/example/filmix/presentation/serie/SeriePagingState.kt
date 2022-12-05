package com.example.filmix.presentation.serie

import androidx.paging.PagingData
import com.example.filmix.domain.model.serie.Serie

sealed class SeriePagingState {
    class Success(val data: PagingData<Serie>) : SeriePagingState()
    class Error(val message: String) : SeriePagingState()
    object Loading : SeriePagingState()
    object Empty : SeriePagingState()
}