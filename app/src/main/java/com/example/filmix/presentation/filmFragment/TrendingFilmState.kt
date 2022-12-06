package com.example.filmix.presentation.filmFragment

import com.example.filmix.features.films.domain.model.FilmDetails

sealed class TrendingFilmState {
    class Success(val data: FilmDetails) : TrendingFilmState()
    class Error(val message: String) : TrendingFilmState()
    object Loading : TrendingFilmState()
    object Empty : TrendingFilmState()
}