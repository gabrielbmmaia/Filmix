package com.example.filmix.data.model.series

import com.google.gson.annotations.SerializedName

data class SerieDto(
    val id: String,
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Int,
    @SerializedName("vote_count")
    val voteCount: Int
)