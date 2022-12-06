package com.example.filmix.features.films.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmix.features.films.data.model.FilmDto
import com.example.filmix.features.films.data.model.FilmRemoteKeys
import com.example.filmix.features.films.data.local.dao.FilmDao
import com.example.filmix.features.films.data.local.dao.FilmRemoteKeysDao

@Database(
    entities = [FilmDto::class, FilmRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao
    abstract fun filmRemoteKeysDao(): FilmRemoteKeysDao

}
