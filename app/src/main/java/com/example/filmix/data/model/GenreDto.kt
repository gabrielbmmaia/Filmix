package com.example.filmix.data.model

import com.example.filmix.domain.model.Genre

data class GenreDto(
    val id: Int,
    val name: String?
) {
    fun toGenre() =
        Genre(
            id = id,
            name = name
        )
}