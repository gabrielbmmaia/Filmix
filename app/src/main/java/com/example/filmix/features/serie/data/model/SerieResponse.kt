package com.example.filmix.features.serie.data.model

import com.google.gson.annotations.SerializedName

data class SerieResponse(
    val page: Int,
    @SerializedName("results")
    val series: List<SerieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
