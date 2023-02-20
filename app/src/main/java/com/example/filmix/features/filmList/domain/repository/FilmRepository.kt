package com.example.filmix.features.filmList.domain.repository

import androidx.paging.PagingData
import com.example.filmix.features.filmList.domain.model.Film
import com.example.filmix.features.filmList.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getPopularFilms(): Flow<PagingData<Film>>

    fun getTopRatedFilms(): Flow<PagingData<Film>>

    fun getSoonFilms(): Flow<PagingData<Film>>

    fun getTheatresFilms(): Flow<PagingData<Film>>

    suspend fun getFilmDetails(filmId: String): FilmDetails
}
