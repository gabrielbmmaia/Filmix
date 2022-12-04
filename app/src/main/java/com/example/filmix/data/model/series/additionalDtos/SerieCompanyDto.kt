package com.example.filmix.data.model.series.additionalDtos

import com.google.gson.annotations.SerializedName

data class SerieCompanyDto(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String,
)