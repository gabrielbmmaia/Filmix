package com.example.filmix.data.di

import androidx.paging.ExperimentalPagingApi
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.remote.TMDBService
import com.example.filmix.data.remote.TrendingService
import com.example.filmix.data.repository.FilmRepositoryImpl
import com.example.filmix.data.repository.TrendingRepositoryImpl
import com.example.filmix.domain.repository.FilmRepository
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
        filmService: TMDBService,
        filmDatabase: FilmDatabase
    ): FilmRepository = FilmRepositoryImpl(
        filmService = filmService,
        filmDatabase = filmDatabase
    )

    @Provides
    fun providesTrendingRepository(
        trendingService: TrendingService
    ): TrendingRepository = TrendingRepositoryImpl(
        trendingService = trendingService
    )
}