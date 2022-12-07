package com.example.filmix.features.shared.domain.repository

import androidx.paging.PagingData
import com.example.filmix.features.serie.domain.model.Serie
import com.example.filmix.features.serie.domain.model.SerieDetails
import kotlinx.coroutines.flow.Flow

interface SerieRepository {

    fun getPopularSerie(): Flow<PagingData<Serie>>

    suspend fun getSerieDetails(serieId: String): SerieDetails
}
