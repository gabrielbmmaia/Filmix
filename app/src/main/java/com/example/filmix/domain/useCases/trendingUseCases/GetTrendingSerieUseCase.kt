package com.example.filmix.domain.useCases.trendingUseCases

import android.util.Log
import com.example.filmix.core.Constants.USECASE_TAG
import com.example.filmix.core.Resource
import com.example.filmix.domain.model.serie.SerieDetails
import com.example.filmix.domain.repository.TrendingRepository
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