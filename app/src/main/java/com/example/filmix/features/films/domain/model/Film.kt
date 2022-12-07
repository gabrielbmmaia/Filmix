package com.example.filmix.features.films.domain.model

data class Film(
    val id: String,
    val title: String?,
    val posterPath: String?,
    val voteAverage: Double?,
)
