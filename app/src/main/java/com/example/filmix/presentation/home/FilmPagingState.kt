package com.example.filmix.presentation.home

import androidx.paging.PagingData
import com.example.filmix.domain.model.film.Film

sealed class FilmPagingState {
    class Success(val data: PagingData<Film>): FilmPagingState()
    class Error(val message: String): FilmPagingState()
    object Loading: FilmPagingState()
    object Empty: FilmPagingState()
}