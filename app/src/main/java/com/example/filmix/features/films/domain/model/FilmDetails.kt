package com.example.filmix.features.films.domain.model

import com.example.filmix.features.shared.Genre

data class FilmDetails(
    val genres: List<Genre>?,
    val id: String,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val voteAverage: Double?,
    val voteCount: Int?
)