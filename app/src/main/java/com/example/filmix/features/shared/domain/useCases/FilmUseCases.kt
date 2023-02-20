package com.example.filmix.features.shared.domain.useCases

import com.example.filmix.features.filmList.domain.useCases.*

data class FilmUseCases(
    val getPopularFilmList: GetPopularFilmListUseCase,
    val getFilmDetails: GetFilmDetailsUseCase,
    val getTopRatedFilms: GetTopRatedFilmsUseCase,
    val getSoonFilmList: GetSoonFilmListUseCase,
    val getTheatreFilmList: GetTheatreFilmListUseCase
)
