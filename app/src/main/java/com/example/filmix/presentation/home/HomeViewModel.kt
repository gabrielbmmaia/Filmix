package com.example.filmix.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.filmix.domain.useCases.FilmUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filmUseCases: FilmUseCases
) : ViewModel() {

    private val _filmList = MutableStateFlow<FilmListState>(FilmListState.Empty)
    val filmList: StateFlow<FilmListState> get() = _filmList

    init {
        loadPopularFilms()
    }

    private fun loadPopularFilms() {
        viewModelScope.launch(IO) {
            filmUseCases.getPopularFilmList().cachedIn(this).collectLatest {
                _filmList.value = FilmListState.Success(it)
            }
        }
    }


}
