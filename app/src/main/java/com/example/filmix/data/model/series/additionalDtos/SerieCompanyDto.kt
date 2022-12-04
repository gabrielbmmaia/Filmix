package com.example.filmix.data.model.series.additionalDtos

import com.example.filmix.domain.model.serie.SerieCompany
import com.google.gson.annotations.SerializedName

data class SerieCompanyDto(
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    val name: String
) {
    fun toSerieCompany() =
        SerieCompany(
            id = id,
            logoPath = logoPath,
            name = name
        )
}