package com.example.filmix.presentation.home

import com.example.filmix.domain.model.Film

sealed class FilmListState {
    class Success(val data: List<Film>): FilmListState()
    class Error(val message: String): FilmListState()
    object Loading: FilmListState()
    object Empty: FilmListState()
}