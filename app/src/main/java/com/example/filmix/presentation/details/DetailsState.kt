package com.example.filmix.presentation.details

import com.example.filmix.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

sealed class DetailsState{
    class Success(val data: Flow<FilmDetails>): DetailsState()
    class Error(val message: String): DetailsState()
    object Loading: DetailsState()
    object Empty: DetailsState()
}
