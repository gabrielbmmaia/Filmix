package com.example.filmix.features.serie.domain.repository

import androidx.paging.PagingData
import com.example.filmix.features.serie.domain.model.Serie
import com.example.filmix.features.serie.domain.model.SerieDetails
import com.example.filmix.features.shared.domain.model.Media
import kotlinx.coroutines.flow.Flow

interface SerieRepository {

    fun getPopularSeries(): Flow<PagingData<Media>>

    fun getTopRatedSeries(): Flow<PagingData<Media>>

    suspend fun getSerieDetails(serieId: String): SerieDetails
}
