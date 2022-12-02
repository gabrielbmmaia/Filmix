package com.example.filmix.data.model.trending

import com.example.filmix.domain.model.TrendingFilm
import com.google.gson.annotations.SerializedName

data class TrendingFilmDto(
    val id: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val title: String,
) {
    fun toTrendingFilm(): TrendingFilm =
        TrendingFilm(
            id = id,
            posterPath = posterPath,
            title = title
        )
}