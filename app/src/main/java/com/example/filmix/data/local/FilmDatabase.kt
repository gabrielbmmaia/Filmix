package com.example.filmix.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmix.data.local.dao.FilmDao
import com.example.filmix.data.local.dao.FilmRemoteKeysDao
import com.example.filmix.data.model.FilmDto
import com.example.filmix.data.model.FilmRemoteKeys

@Database(
    entities = [FilmDto::class, FilmRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase: RoomDatabase() {

    abstract fun filmDao(): FilmDao
    abstract fun filmRemoteKeysDao(): FilmRemoteKeysDao

}