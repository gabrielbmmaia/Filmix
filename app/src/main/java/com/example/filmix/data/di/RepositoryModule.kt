package com.example.filmix.data.di

import androidx.paging.ExperimentalPagingApi
import com.example.filmix.data.local.FilmDatabase
import com.example.filmix.data.remote.TMDBService
import com.example.filmix.data.repository.FilmRepositoryImpl
import com.example.filmix.domain.repository.FilmRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesFilmRepository(
        filmService: TMDBService,
        filmDatabase: FilmDatabase
    ): FilmRepository =
        FilmRepositoryImpl(
            filmService = filmService,
            filmDatabase = filmDatabase
        )


}