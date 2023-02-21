package com.example.filmix.features.shared.data.model

import com.example.filmix.features.shared.domain.model.Media
import com.google.gson.annotations.SerializedName

data class MediaDto(
    val id: String,
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
) {
    fun toMedia(): Media {
        return Media(
            id = id,
            title = title,
            posterPath = posterPath,
            voteAverage = voteAverage
        )
    }
}
