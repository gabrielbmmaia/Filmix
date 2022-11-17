package com.example.filmix.domain.repository

import androidx.paging.PagingData
import com.example.filmix.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getAllFilms(): Flow<PagingData<Film>>

}