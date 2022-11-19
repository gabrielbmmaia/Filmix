package com.example.filmix.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.filmix.domain.useCases.FilmUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val filmUseCases: FilmUseCases
) : ViewModel() {

//    private val _filmList = MutableStateFlow<FilmListState>(FilmListState.Empty)
//    val filmList: StateFlow<FilmListState> get() = _filmList




        val listData = filmUseCases.getPopularFilmList().cachedIn(viewModelScope)




}
