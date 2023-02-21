package com.example.filmix.features.serie.domain.useCases

import androidx.paging.PagingData
import com.example.filmix.features.shared.domain.model.Media
import com.example.filmix.features.serie.domain.repository.SerieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularSerieUseCase @Inject constructor(
    private val serieRepository: SerieRepository
) {
    operator fun invoke(): Flow<PagingData<Media>> = serieRepository.getPopularSeries()
}
