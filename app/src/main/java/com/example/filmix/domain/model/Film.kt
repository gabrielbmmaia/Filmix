package com.example.filmix.domain.model

import java.io.Serializable

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
): Serializable
