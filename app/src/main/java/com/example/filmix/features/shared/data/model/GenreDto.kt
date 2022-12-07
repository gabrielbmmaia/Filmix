package com.example.filmix.features.shared.data.model

import com.example.filmix.features.shared.domain.model.Genre

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
