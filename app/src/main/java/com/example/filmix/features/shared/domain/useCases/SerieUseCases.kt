package com.example.filmix.features.shared.domain.useCases

import com.example.filmix.features.serie.domain.useCases.GetPopularSerieListUseCase
import com.example.filmix.features.serie.domain.useCases.GetSerieDetailsUseCase
import com.example.filmix.features.serie.domain.useCases.GetTopRatedSerieListUseCase

data class SerieUseCases(
    val getPopularSerieList: GetPopularSerieListUseCase,
    val getTopRatedSerieList:GetTopRatedSerieListUseCase,
    val getSerieDetails: GetSerieDetailsUseCase
)
