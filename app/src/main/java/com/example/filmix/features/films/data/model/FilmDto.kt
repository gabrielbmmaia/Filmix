package com.example.filmix.features.films.data.model

import androidx.room.PrimaryKey
import com.example.filmix.features.films.domain.model.Film
import com.google.gson.annotations.SerializedName

data class FilmDto(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
) {
    fun toFilm(): Film {
        return Film(
            id = id,
            title = title,
            posterPath = posterPath,
            voteAverage = voteAverage
        )
    }
}
