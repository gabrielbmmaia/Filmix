package com.example.filmix.features.shared.domain.useCases

import com.example.filmix.features.filmList.domain.useCases.GetFilmDetailsUseCase
import com.example.filmix.features.filmList.domain.useCases.GetPopularFilmListUseCase
import com.example.filmix.features.filmList.domain.useCases.GetTopRatedFilmsUseCase

data class FilmUseCases(
    val getPopularFilmList: GetPopularFilmListUseCase,
    val getFilmDetails: GetFilmDetailsUseCase,
    val getTopRatedFilms: GetTopRatedFilmsUseCase
)
