package com.example.filmix.features.shared

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
