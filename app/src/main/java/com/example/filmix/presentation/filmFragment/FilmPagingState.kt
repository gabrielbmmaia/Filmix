package com.example.filmix.presentation.filmFragment

import androidx.paging.PagingData
import com.example.filmix.features.films.domain.model.Film

sealed class FilmPagingState {
    class Success(val data: PagingData<Film>): FilmPagingState()
    class Error(val message: String): FilmPagingState()
    object Loading: FilmPagingState()
    object Empty: FilmPagingState()
}