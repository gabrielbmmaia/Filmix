package com.example.filmix.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmix.core.Resource
import com.example.filmix.features.films.domain.useCases.FilmUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val filmUseCases: FilmUseCases
) : ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Empty)
    val detailsState: StateFlow<DetailsState> get() = _detailsState

    fun loadDetails(filmId: String) {
        viewModelScope.launch(IO) {
            filmUseCases.getFilmDetails(filmId).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _detailsState.value =
                            DetailsState.Error(message = result.message!!)
                    }

                    is Resource.Success -> {
                        _detailsState.value =
                            DetailsState.Success(data = result.data!!)
                    }

                    Resource.Loading -> {
                        _detailsState.value =
                            DetailsState.Loading
                    }
                }
            }
        }
    }
}