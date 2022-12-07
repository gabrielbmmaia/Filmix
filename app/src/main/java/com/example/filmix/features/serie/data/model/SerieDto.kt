package com.example.filmix.features.serie.data.model

import com.example.filmix.features.serie.domain.model.Serie
import com.google.gson.annotations.SerializedName

data class SerieDto(
    val id: String,
    @SerializedName("name")
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
) {
    fun toSerie() =
        Serie(
            id = id,
            title = title,
            posterPath = posterPath,
            voteAverage = voteAverage,
        )
}
