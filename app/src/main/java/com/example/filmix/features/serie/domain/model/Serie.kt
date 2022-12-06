package com.example.filmix.features.serie.domain.model

data class Serie(
    val id: String,
    val title: String?,
    val posterPath: String?,
    val voteAverage: Int?,
    val voteCount: Int?
)
