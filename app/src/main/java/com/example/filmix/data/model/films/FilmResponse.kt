package com.example.filmix.data.model.films

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    val page: Int,
    @SerializedName("results")
    val films: List<FilmDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)