package com.example.filmix.data.model.trending

import com.google.gson.annotations.SerializedName

data class TrendingFilmResponse(
    val page: Int,
    val results: List<TrendingFilmDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)