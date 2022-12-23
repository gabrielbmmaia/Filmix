package com.example.filmix.presentation.detailsFragment

import com.example.filmix.features.filmList.domain.model.FilmDetails

sealed class DetailsState{
    class Success(val data: FilmDetails): DetailsState()
    class Error(val message: String): DetailsState()
    object Loading: DetailsState()
    object Empty: DetailsState()
}
