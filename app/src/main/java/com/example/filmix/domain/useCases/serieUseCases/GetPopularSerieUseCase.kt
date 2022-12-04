package com.example.filmix.domain.useCases.serieUseCases

import androidx.paging.PagingData
import com.example.filmix.domain.model.serie.Serie
import com.example.filmix.domain.repository.SerieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularSerieUseCase @Inject constructor(
    private val serieRepository: SerieRepository
) {
    operator fun invoke(): Flow<PagingData<Serie>> = serieRepository.getPopularSerie()
}