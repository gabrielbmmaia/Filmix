package com.example.filmix.domain.useCases.trendingUseCases

data class TrendingUseCases(
    val getTrendingFilm: GetTrendingFilmUseCase,
    val getTrendingSerie: GetTrendingSerieUseCase
)