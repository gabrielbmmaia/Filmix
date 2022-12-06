package com.example.filmix.features.films.domain.repository

import androidx.paging.PagingData
import com.example.filmix.features.films.domain.model.Film
import com.example.filmix.features.films.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getPopularFilms(): Flow<PagingData<Film>>

    suspend fun getFilmDetails(filmId: String): FilmDetails
}
