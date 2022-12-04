package com.example.filmix.domain.di

import com.example.filmix.domain.repository.FilmRepository
import com.example.filmix.domain.repository.TrendingRepository
import com.example.filmix.domain.useCases.filmUseCases.FilmUseCases
import com.example.filmix.domain.useCases.filmUseCases.GetFilmDetailsUseCase
import com.example.filmix.domain.useCases.filmUseCases.GetPopularFilmListUseCase
import com.example.filmix.domain.useCases.trendingUseCases.GetTrendingFilmUseCase
import com.example.filmix.domain.useCases.trendingUseCases.TrendingUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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