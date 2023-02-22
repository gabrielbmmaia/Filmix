package com.example.filmix.features.serie.domain.useCases

import androidx.paging.PagingData
import com.example.filmix.features.serie.domain.repository.SerieRepository
import com.example.filmix.features.shared.domain.model.Media
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedSerieListUseCase @Inject constructor(
    private val serieRepository: SerieRepository
) {
    operator fun invoke(): Flow<PagingData<Media>> = serieRepository.getTopRatedSeries()
}