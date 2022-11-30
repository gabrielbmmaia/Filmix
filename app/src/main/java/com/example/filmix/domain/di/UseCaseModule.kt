package com.example.filmix.domain.di

import com.example.filmix.domain.repository.FilmRepository
import com.example.filmix.domain.repository.TrendingRepository
import com.example.filmix.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesFilmUseCases(filmRepository: FilmRepository) =
        FilmUseCases(
            getPopularFilmList = GetPopularFilmListUseCase(filmRepository = filmRepository),
            getFilmDetails = GetFilmDetailsUseCase(filmRepository = filmRepository)
        )

    @Provides
    fun providesTrendingUseCases(trendingRepository: TrendingRepository) =
        TrendingUseCases(
            getTrendingFilm = GetTrendingFilmUseCase(trendingRepository = trendingRepository)
        )

}