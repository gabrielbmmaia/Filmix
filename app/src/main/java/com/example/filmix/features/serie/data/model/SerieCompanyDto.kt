package com.example.filmix.features.serie.data.model

import com.example.filmix.features.serie.domain.model.SerieCompany
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
