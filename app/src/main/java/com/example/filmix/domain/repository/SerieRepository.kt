package com.example.filmix.domain.repository

import androidx.paging.PagingData
import com.example.filmix.domain.model.serie.Serie
import com.example.filmix.domain.model.serie.SerieDetails
import kotlinx.coroutines.flow.Flow

interface SerieRepository {

    fun getPopularSerie(): Flow<PagingData<Serie>>

    suspend fun getSerieDetails(serieId: String): SerieDetails
}