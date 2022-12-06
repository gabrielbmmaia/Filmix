package com.example.filmix.features.films.domain.model

data class Film(
    val id: String,
    val originalLanguage: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Long
)
