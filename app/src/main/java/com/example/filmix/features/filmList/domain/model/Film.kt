package com.example.filmix.features.filmList.domain.model

data class Film(
    val id: String,
    val title: String?,
    val posterPath: String?,
    val voteAverage: Double?,
)
