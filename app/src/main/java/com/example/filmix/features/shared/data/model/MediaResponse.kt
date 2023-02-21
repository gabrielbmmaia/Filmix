package com.example.filmix.features.shared.data.model

import com.example.filmix.features.shared.data.model.MediaDto
import com.google.gson.annotations.SerializedName

data class MediaResponse(
    val page: Int,
    @SerializedName("results")
    val films: List<MediaDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
