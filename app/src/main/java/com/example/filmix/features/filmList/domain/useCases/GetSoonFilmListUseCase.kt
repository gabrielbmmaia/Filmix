package com.example.filmix.features.filmList.domain.useCases

import androidx.paging.PagingData
import com.example.filmix.features.shared.domain.model.Media
import com.example.filmix.features.filmList.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSoonFilmListUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    operator fun invoke(): Flow<PagingData<Media>> = filmRepository.getSoonFilms()
}