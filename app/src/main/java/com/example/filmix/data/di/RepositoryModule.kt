package com.example.filmix.data.di

import androidx.paging.ExperimentalPagingApi
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.local.SerieDatabase
import com.example.filmix.data.remote.FilmService
import com.example.filmix.data.remote.SerieService
import com.example.filmix.data.remote.TrendingService
import com.example.filmix.data.repository.FilmRepositoryImpl
import com.example.filmix.data.repository.SerieRepositoryImpl
import com.example.filmix.data.repository.TrendingRepositoryImpl
import com.example.filmix.domain.repository.FilmRepository
import com.example.filmix.domain.repository.SerieRepository
import com.example.filmix.domain.repository.TrendingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesFilmRepository(
        filmService: FilmService,
        filmDatabase: FilmDatabase
    ): FilmRepository = FilmRepositoryImpl(
        filmService = filmService,
        filmDatabase = filmDatabase
    )

    @Provides
    fun providesTrendingRepository(
        trendingService: TrendingService,
        filmService: FilmService,
        serieService: SerieService
    ): TrendingRepository = TrendingRepositoryImpl(
        trendingService = trendingService,
        filmService = filmService,
        serieService = serieService
    )

    @Provides
    fun providesSerieRepository(
        serieService: SerieService,
        serieDatabase: SerieDatabase
    ): SerieRepository = SerieRepositoryImpl(
        serieService = serieService,
        serieDatabase = serieDatabase
    )
}