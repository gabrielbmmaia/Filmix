package com.example.filmix.presentation.filmFragment

import androidx.paging.PagingData
import com.example.filmix.features.shared.domain.model.Media

sealed class FilmPagingState {
    class Success(val data: PagingData<Media>): FilmPagingState()
    class Error(val message: String): FilmPagingState()
    object Loading: FilmPagingState()
    object Empty: FilmPagingState()
}