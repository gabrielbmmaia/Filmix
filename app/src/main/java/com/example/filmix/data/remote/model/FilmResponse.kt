package com.example.filmix.data.remote.model

import com.squareup.moshi.Json

data class FilmResponse(
    val page: Int,
    @Json(name= "results")
    val films: List<FilmDto>,
    @Json(name= "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults:Int
)