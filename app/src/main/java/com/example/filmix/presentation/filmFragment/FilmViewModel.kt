package com.example.filmix.presentation.filmFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.filmix.core.Resource
import com.example.filmix.features.shared.domain.useCases.FilmUseCases
import com.example.filmix.features.shared.presentation.MediaState
import com.example.filmix.features.trending.domain.useCases.TrendingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel @Inject constructor(
    private val filmUseCases: FilmUseCases,
    private val trendingUseCases: TrendingUseCases
) : ViewModel() {

    private val _filmPopularList = MutableStateFlow<MediaState>(MediaState.Empty)
    val filmPopularList: StateFlow<MediaState> get() = _filmPopularList

    private val _filmRatedList = MutableStateFlow<MediaState>(MediaState.Empty)
    val filmRatedList: StateFlow<MediaState> get() = _filmRatedList

    private val _filmSoonList = MutableStateFlow<MediaState>(MediaState.Empty)
    val filmSoonList: StateFlow<MediaState> get() = _filmSoonList

    private val _filmTheatreList = MutableStateFlow<MediaState>(MediaState.Empty)
    val filmTheatreList: StateFlow<MediaState> get() = _filmTheatreList

    private val _trendingFilm = MutableStateFlow<TrendingFilmState>(TrendingFilmState.Empty)
    val trendingFilm: StateFlow<TrendingFilmState> get() = _trendingFilm

    init {
        loadTrendingFilm()
        loadPopularFilms()
        loadTopRatedFilms()
        loadInSoonFilms()
        loadInTheatreFilms()
    }

    private fun loadPopularFilms() {
        viewModelScope.launch {
            filmUseCases.getPopularFilmList().cachedIn(scope = this).collectLatest {
                _filmPopularList.value = MediaState.Success(it)
            }
        }
    }

    private fun loadTopRatedFilms() {
        viewModelScope.launch {
            filmUseCases.getTopRatedFilmList().cachedIn(scope = this).collectLatest {
                _filmRatedList.value = MediaState.Success(it)
            }
        }
    }

    private fun loadInSoonFilms() {
        viewModelScope.launch {
            filmUseCases.getSoonFilmList().cachedIn(scope = this).collectLatest {
                _filmSoonList.value = MediaState.Success(it)
            }
        }
    }

    private fun loadInTheatreFilms() {
        viewModelScope.launch {
            filmUseCases.getTheatreFilmList().cachedIn(scope = this).collectLatest {
                _filmTheatreList.value = MediaState.Success(it)
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
