package com.example.filmix.presentation.home

import androidx.paging.PagingData
import com.example.filmix.domain.model.Film

sealed class FilmListState {
    class Success(val data: PagingData<Film>): FilmListState()
    class Error(val message: String): FilmListState()
    object Loading: FilmListState()
    object Empty: FilmListState()
}