package com.example.filmix.di

import com.example.filmix.features.films.domain.repository.FilmRepository
import com.example.filmix.features.films.domain.useCases.FilmUseCases
import com.example.filmix.features.films.domain.useCases.GetFilmDetailsUseCase
import com.example.filmix.features.films.domain.useCases.GetPopularFilmListUseCase
import com.example.filmix.features.serie.domain.repository.SerieRepository
import com.example.filmix.features.serie.domain.useCases.GetPopularSerieUseCase
import com.example.filmix.features.serie.domain.useCases.GetSerieDetailsUseCase
import com.example.filmix.features.serie.domain.useCases.SerieUseCases
import com.example.filmix.features.trending.domain.repository.TrendingRepository
import com.example.filmix.features.trending.domain.useCases.GetTrendingFilmUseCase
import com.example.filmix.features.trending.domain.useCases.GetTrendingSerieUseCase
import com.example.filmix.features.trending.domain.useCases.TrendingUseCases
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
            getTrendingFilm = GetTrendingFilmUseCase(trendingRepository = trendingRepository),
            getTrendingSerie = GetTrendingSerieUseCase(trendingRepository = trendingRepository)
        )

    @Provides
    fun providesSerieUseCases(serieRepository: SerieRepository) =
        SerieUseCases(
            getPopularSerie = GetPopularSerieUseCase(serieRepository = serieRepository),
            getSerieDetails = GetSerieDetailsUseCase(serieRepository = serieRepository)
        )
}