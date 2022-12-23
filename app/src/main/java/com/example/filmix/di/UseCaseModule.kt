package com.example.filmix.di

import com.example.filmix.features.filmList.domain.repository.FilmRepository
import com.example.filmix.features.filmList.domain.useCases.GetFilmDetailsUseCase
import com.example.filmix.features.filmList.domain.useCases.GetPopularFilmListUseCase
import com.example.filmix.features.filmList.domain.useCases.GetTopRatedFilmsUseCase
import com.example.filmix.features.serie.domain.useCases.GetPopularSerieUseCase
import com.example.filmix.features.serie.domain.useCases.GetSerieDetailsUseCase
import com.example.filmix.features.shared.domain.repository.SerieRepository
import com.example.filmix.features.shared.domain.useCases.FilmUseCases
import com.example.filmix.features.shared.domain.useCases.SerieUseCases
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
            getFilmDetails = GetFilmDetailsUseCase(filmRepository = filmRepository),
            getTopRatedFilms = GetTopRatedFilmsUseCase(filmRepository = filmRepository)
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
