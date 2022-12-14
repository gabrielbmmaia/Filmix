package com.example.filmix.features.filmList.domain.useCases

import android.util.Log
import com.example.filmix.core.Constants.USECASE_TAG
import com.example.filmix.core.Resource
import com.example.filmix.features.filmList.domain.model.FilmDetails
import com.example.filmix.features.filmList.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmDetailsUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {

    operator fun invoke(filmId: String): Flow<Resource<FilmDetails>> = flow {
        try {
            emit(Resource.Loading)

            val filmDetails = filmRepository.getFilmDetails(filmId = filmId)
            emit(Resource.Success(filmDetails))

        } catch (e: Exception) {
            Log.e(USECASE_TAG, e.stackTraceToString())
            emit(Resource.Error(message = "Não foi possível se conectar"))
        }
    }
}
