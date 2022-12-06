package com.example.filmix.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.filmix.core.Resource
import com.example.filmix.features.films.domain.useCases.FilmUseCases
import com.example.filmix.features.trending.domain.useCases.TrendingUseCases
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

    private val _trendingFilm = MutableStateFlow<TrendingFilmState>(TrendingFilmState.Empty)
    val trendingFilm: StateFlow<TrendingFilmState> get() = _trendingFilm

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
                        _trendingFilm.value = TrendingFilmState.Error(message = result.message!!)
                    }

                    Resource.Loading -> {
                        _trendingFilm.value = TrendingFilmState.Loading
                    }

                    is Resource.Success -> {
                        _trendingFilm.value = TrendingFilmState.Success(data = result.data!!)
                    }
                }
            }
        }
    }
}
