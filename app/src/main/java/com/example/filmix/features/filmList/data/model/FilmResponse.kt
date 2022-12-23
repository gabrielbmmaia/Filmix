package com.example.filmix.features.filmList.data.model

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
