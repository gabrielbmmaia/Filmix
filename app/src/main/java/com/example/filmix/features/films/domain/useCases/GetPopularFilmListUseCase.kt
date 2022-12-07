package com.example.filmix.features.films.domain.useCases

import androidx.paging.PagingData
import com.example.filmix.features.films.domain.model.Film
import com.example.filmix.features.shared.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularFilmListUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    operator fun invoke(): Flow<PagingData<Film>> = filmRepository.getPopularFilms()
}
