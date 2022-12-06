package com.example.filmix.features.serie.domain.model

data class SerieSeason(
    val id: Int,
    val airDate: String?,
    val episodeCount: Int?,
    val name: String?,
    val posterPath: String?,
    val seasonNumber: Int?
)
