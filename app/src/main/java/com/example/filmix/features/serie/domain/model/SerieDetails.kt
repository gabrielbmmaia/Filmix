package com.example.filmix.features.serie.domain.model

import com.example.filmix.features.shared.Genre

data class SerieDetails(
    val id: String,
    val backdropPath: String,
    val lastAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val name: String,
    val companies: List<SerieCompany>,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val overview: String,
    val posterPath: String,
    val seasons: List<SerieSeason>,
    val voteAverage: Double,
    val voteCount: Int
)
