package com.example.filmix.presentation.details

import androidx.lifecycle.ViewModel
import com.example.filmix.domain.useCases.FilmUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class DetailsViewModel(
    private val filmUseCases: FilmUseCases
) : ViewModel() {

    private val _details = MutableStateFlow<DetailsState>(DetailsState.Empty)
    val details = _details.asStateFlow()

    init {
        loadDetails()
    }

    private fun loadDetails(){

    }
}