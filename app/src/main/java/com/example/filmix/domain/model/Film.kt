package com.example.filmix.domain.model

data class Film(
    val id: Int,
    val idGenres: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: Long
)