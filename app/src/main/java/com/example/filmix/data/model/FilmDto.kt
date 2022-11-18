package com.example.filmix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmix.core.Constants.FILM_TABLE
import com.example.filmix.domain.model.Film
import com.squareup.moshi.Json

@Entity(tableName = FILM_TABLE)
data class FilmDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @Json(name = "genre_ids")
    val idGenres: List<Int>?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_title")
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    @Json(name = "vote_count")
    val voteCount: Long?
) {
    fun toFilm(): Film {
        return Film(
            id = id,
            idGenres = idGenres ?: emptyList(),
            originalLanguage = originalLanguage ?: "",
            originalTitle = originalTitle ?: "",
            overview = overview ?: "",
            popularity = popularity ?: 0.0,
            posterPath = posterPath ?: "",
            releaseDate = releaseDate  ?: "",
            voteAverage = voteAverage ?: 0.0,
            voteCount = voteCount ?: 0
        )
    }
}
