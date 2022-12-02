package com.example.filmix.domain.useCases

import android.util.Log
import com.example.filmix.core.Constants.USECASE_TAG
import com.example.filmix.core.Resource
import com.example.filmix.domain.model.FilmDetails
import com.example.filmix.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTrendingFilmUseCase @Inject constructor(
    private val trendingRepository: TrendingRepository
) {

    suspend operator fun invoke(): Flow<Resource<FilmDetails>> = flow {
        try {
            emit(Resource.Loading)

            val trendingFilm = trendingRepository.getTrendingFilm()
            emit(Resource.Success(data = trendingFilm))

        } catch (e: Exception) {
            Log.e(USECASE_TAG, e.stackTraceToString())
            emit(Resource.Error(message = "Não foi possível se conectar"))
        }
    }
}