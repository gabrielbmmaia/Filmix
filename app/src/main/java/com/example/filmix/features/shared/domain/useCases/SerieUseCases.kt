package com.example.filmix.features.shared.domain.useCases

import com.example.filmix.features.serie.domain.useCases.GetPopularSerieUseCase
import com.example.filmix.features.serie.domain.useCases.GetSerieDetailsUseCase

data class SerieUseCases(
    val getPopularSerie: GetPopularSerieUseCase,
    val getSerieDetails: GetSerieDetailsUseCase
)
