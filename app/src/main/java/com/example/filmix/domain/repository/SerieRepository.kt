package com.example.filmix.domain.repository

import androidx.paging.PagingData
import com.example.filmix.domain.model.Serie
import kotlinx.coroutines.flow.Flow

interface SerieRepository {

    fun getPopularSerie(): Flow<PagingData<Serie>>

}