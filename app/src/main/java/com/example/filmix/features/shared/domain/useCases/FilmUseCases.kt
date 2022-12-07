package com.example.filmix.features.shared.domain.useCases

import com.example.filmix.features.films.domain.useCases.GetFilmDetailsUseCase
import com.example.filmix.features.films.domain.useCases.GetPopularFilmListUseCase

data class FilmUseCases(
    val getPopularFilmList: GetPopularFilmListUseCase,
    val getFilmDetails: GetFilmDetailsUseCase
)
