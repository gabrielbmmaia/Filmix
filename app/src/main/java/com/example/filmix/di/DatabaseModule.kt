package com.example.filmix.di

import android.content.Context
import androidx.room.Room
import com.example.filmix.core.Constants.SERIE_DATABASE
import com.example.filmix.features.serie.data.local.SerieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

//    @Provides
//    @Singleton
//    fun provideFilmDatabase(
//        @ApplicationContext context: Context
//    ): FilmDatabase {
//        return Room.databaseBuilder(
//            context,
//            FilmDatabase::class.java,
//            FILM_DATABASE
//        ).fallbackToDestructiveMigration()
//            .build()
//    }

    @Provides
    @Singleton
    fun provideSerieDatabase(
        @ApplicationContext context: Context
    ): SerieDatabase {
        return Room.databaseBuilder(
            context,
            SerieDatabase::class.java,
            SERIE_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }
}
