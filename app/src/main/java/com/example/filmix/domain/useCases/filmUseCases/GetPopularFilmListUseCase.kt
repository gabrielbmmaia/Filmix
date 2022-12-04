package com.example.filmix.domain.useCases.filmUseCases

import androidx.paging.PagingData
import com.example.filmix.domain.model.Film
import com.example.filmix.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularFilmListUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    operator fun invoke(): Flow<PagingData<Film>> = filmRepository.getPopularFilms()
}
