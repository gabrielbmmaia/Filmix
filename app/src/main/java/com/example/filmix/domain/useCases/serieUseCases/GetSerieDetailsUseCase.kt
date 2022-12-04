package com.example.filmix.domain.useCases.serieUseCases

import android.util.Log
import com.example.filmix.core.Constants.USECASE_TAG
import com.example.filmix.core.Resource
import com.example.filmix.domain.model.serie.SerieDetails
import com.example.filmix.domain.repository.SerieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSerieDetailsUseCase @Inject constructor(
    private val serieRepository: SerieRepository
) {

    suspend operator fun invoke(serieId: String): Flow<Resource<SerieDetails>> = flow {
        try {
            emit(Resource.Loading)

            val serieDetails = serieRepository.getSerieDetails(serieId = serieId)
            emit(Resource.Success(data = serieDetails))

        } catch (e: Exception) {
            Log.e(USECASE_TAG, e.stackTraceToString())
            emit(Resource.Error(message = "Não foi possível se conectar"))
        }
    }
}