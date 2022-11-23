package com.example.filmix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmix.core.Constants.FILM_TABLE
import com.example.filmix.domain.model.Film
import com.google.gson.annotations.SerializedName

@Entity(tableName = FILM_TABLE)
data class FilmDto(
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("title")
    val title: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Long?
) {
    fun toFilm(): Film {
        return Film(
            id = id,
            originalLanguage = originalLanguage ?: "",
            title = title ?: "",
            overview = overview ?: "",
            popularity = popularity ?: 0.0,
            posterPath = posterPath,
            releaseDate = releaseDate  ?: "",
            voteAverage = voteAverage ?: 0.0,
            voteCount = voteCount ?: 0
        )
    }
}
