package com.example.filmix.presentation.serie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.filmix.core.Resource
import com.example.filmix.domain.useCases.serieUseCases.SerieUseCases
import com.example.filmix.domain.useCases.trendingUseCases.TrendingUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SerieViewModel @Inject constructor(
    private val serieUseCase: SerieUseCases,
    private val trendingUseCases: TrendingUseCases
) : ViewModel() {

    private val _serieList = MutableStateFlow<SeriePagingState>(SeriePagingState.Empty)
    val serieList: StateFlow<SeriePagingState> get() = _serieList

    private val _trendingSerie = MutableStateFlow<TrendingSerieState>(TrendingSerieState.Empty)
    val trendingSerie: StateFlow<TrendingSerieState> get() = _trendingSerie

    init {
        loadTrendingSerie()
        loadPopularSeries()
    }

    private fun loadPopularSeries() {
        viewModelScope.launch {
            serieUseCase.getPopularSerie().cachedIn(this).collectLatest {
                _serieList.value = SeriePagingState.Success(data = it)
            }
        }
    }

    private fun loadTrendingSerie() {
        viewModelScope.launch {
            trendingUseCases.getTrendingSerie().collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _trendingSerie.value = TrendingSerieState.Error(message = result.message!!)
                    }

                    Resource.Loading -> {
                        _trendingSerie.value = TrendingSerieState.Loading
                    }

                    is Resource.Success -> {
                        _trendingSerie.value = TrendingSerieState.Success(data = result.data!!)
                    }
                }
            }
        }
    }
}