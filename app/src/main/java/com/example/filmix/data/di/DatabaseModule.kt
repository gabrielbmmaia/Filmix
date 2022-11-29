package com.example.filmix.data.di

import android.content.Context
import androidx.room.Room
import com.example.filmix.core.Constants.FILM_DATABASE
import com.example.filmix.data.local.FilmDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): FilmDatabase {
        return Room.databaseBuilder(
            context,
            FilmDatabase::class.java,
            FILM_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }
}
