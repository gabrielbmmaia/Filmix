package com.example.filmix.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.filmix.core.Resource
import com.example.filmix.domain.useCases.FilmUseCases
import com.example.filmix.domain.useCases.TrendingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filmUseCases: FilmUseCases,
    private val trendingUseCases: TrendingUseCases
) : ViewModel() {

    private val _filmList = MutableStateFlow<FilmPagingState>(FilmPagingState.Empty)
    val filmList: StateFlow<FilmPagingState> get() = _filmList

    private val _trendingFilm = MutableStateFlow<TrendingState>(TrendingState.Empty)
    val trendingFilm: StateFlow<TrendingState> get() = _trendingFilm

    init {
        loadPopularFilms()
        loadTrendingFilm()
    }

    private fun loadPopularFilms() {
        viewModelScope.launch {
            filmUseCases.getPopularFilmList().cachedIn(this).collectLatest {
                _filmList.value = FilmPagingState.Success(it)
            }
        }
    }

    private fun loadTrendingFilm() {
        viewModelScope.launch {
            trendingUseCases.getTrendingFilm().collectLatest { result ->
                when (result) {

                    is Resource.Error -> {
                        _trendingFilm.value = TrendingState.Error(message = result.message!!)
                    }

                    Resource.Loading -> {
                        _trendingFilm.value = TrendingState.Loading
                    }

                    is Resource.Success -> {
                        _trendingFilm.value = TrendingState.Success(data = result.data!!)
                    }
                }
            }
        }
    }

}
