package com.example.filmix.features.trending.data.model

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    val page: Int,
    val results: List<TrendingItemDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
