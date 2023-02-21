package com.example.filmix.features.filmList.domain.repository

import androidx.paging.PagingData
import com.example.filmix.features.shared.domain.model.Media
import com.example.filmix.features.filmList.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getPopularFilms(): Flow<PagingData<Media>>

    fun getTopRatedFilms(): Flow<PagingData<Media>>

    fun getSoonFilms(): Flow<PagingData<Media>>

    fun getTheatresFilms(): Flow<PagingData<Media>>

    suspend fun getFilmDetails(filmId: String): FilmDetails
}
