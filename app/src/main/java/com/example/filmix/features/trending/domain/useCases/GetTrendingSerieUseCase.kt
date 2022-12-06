package com.example.filmix.features.trending.domain.useCases

import android.util.Log
import com.example.filmix.core.Constants.USECASE_TAG
import com.example.filmix.core.Resource
import com.example.filmix.features.serie.domain.model.SerieDetails
import com.example.filmix.features.trending.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrendingSerieUseCase @Inject constructor(
    private val trendingRepository: TrendingRepository
) {

    suspend operator fun invoke(): Flow<Resource<SerieDetails>> = flow {
        try {
            emit(Resource.Loading)

            val trendingSerie = trendingRepository.getTrendingSerie()
            emit(Resource.Success(data = trendingSerie))

        } catch (e: Exception) {
            Log.e(USECASE_TAG, e.stackTraceToString())
            emit(Resource.Error(message = "Não foi possível se conectar"))
        }
    }
}
