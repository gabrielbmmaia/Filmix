package com.example.filmix.features.serie.data.model

import com.example.filmix.features.serie.domain.model.SerieSeason
import com.google.gson.annotations.SerializedName

data class SerieSeasonDto(
    val id: Int,
    @SerializedName("air_date")
    val airDate: String?,
    @SerializedName("episode_count")
    val episodeCount: Int?,
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("season_number")
    val seasonNumber: Int?
) {
    fun toSerieSeason() =
        SerieSeason(
            id = id,
            airDate = airDate,
            episodeCount = episodeCount,
            name = name,
            posterPath = posterPath,
            seasonNumber = seasonNumber
        )
}
