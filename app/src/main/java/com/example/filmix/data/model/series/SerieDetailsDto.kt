package com.example.filmix.data.model.series

import com.example.filmix.data.model.GenreDto
import com.example.filmix.data.model.series.additionalDtos.SerieCompanyDto
import com.example.filmix.data.model.series.additionalDtos.SerieSeasonDto
import com.example.filmix.domain.model.serie.SerieDetails
import com.google.gson.annotations.SerializedName

data class SerieDetailsDto(
    val id: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("last_air_date")
    val lastAirDate: String,
    val genres: List<GenreDto>,
    val homepage: String,
    val name: String,
    @SerializedName("networks")
    val companies: List<SerieCompanyDto>, // testando, variavel com as empresas envolvidas "netflix" etc
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    val seasons: List<SerieSeasonDto>,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toSerieDetails() =
        SerieDetails(
            id = id,
            backdropPath = backdropPath,
            lastAirDate = lastAirDate,
            genres = genres.map { it.toGenre() },
            homepage = homepage,
            name = name,
            companies = companies.map { it.toSerieCompany() },
            numberOfEpisodes = numberOfEpisodes,
            numberOfSeasons = numberOfEpisodes,
            overview = overview,
            posterPath = posterPath,
            seasons = seasons.map { it.toSerieSeason() },
            voteAverage = voteAverage,
            voteCount = voteCount
        )
}