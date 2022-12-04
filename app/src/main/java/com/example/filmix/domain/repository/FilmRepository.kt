package com.example.filmix.domain.repository

import androidx.paging.PagingData
import com.example.filmix.domain.model.film.Film
import com.example.filmix.domain.model.film.FilmDetails
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getPopularFilms(): Flow<PagingData<Film>>

    fun getSearchedFilms(query: String): Flow<PagingData<Film>>

    suspend fun getFilmDetails(filmId: String): FilmDetails
}