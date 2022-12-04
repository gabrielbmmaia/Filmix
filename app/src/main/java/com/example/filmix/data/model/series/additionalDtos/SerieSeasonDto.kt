package com.example.filmix.data.model.series.additionalDtos

import com.google.gson.annotations.SerializedName

data class SerieSeasonDto(
    val id: Int,
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("episode_count")
    val episodeCount: Int,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("season_number")
    val seasonNumber: Int
)