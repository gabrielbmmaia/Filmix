package com.example.filmix.domain.useCases

import com.example.filmix.domain.repository.FilmRepository
import javax.inject.Inject

class GetPopularFilmListUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    operator fun invoke() = filmRepository.getAllFilms()
}
