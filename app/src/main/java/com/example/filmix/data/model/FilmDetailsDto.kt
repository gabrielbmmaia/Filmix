package com.example.filmix.data.model

import com.example.filmix.domain.model.FilmDetails
import com.google.gson.annotations.SerializedName

data class FilmDetailsDto(
    val genres: List<Genre>,
    val id: Int,
    @SerializedName("imdb_id")
    val imdbId: String?,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    val runtime: Int?,
    val status: String,
    val tagline: String,
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toFilmDetails(): FilmDetails {
        return FilmDetails(
            genres = genres,
            id = id,
            imdbId = imdbId,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            status = status,
            tagline = tagline,
            title = title,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}