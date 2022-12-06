package com.example.filmix.features.serie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filmix.features.serie.data.local.dao.SerieDao
import com.example.filmix.features.serie.data.local.dao.SerieRemoteKeysDao
import com.example.filmix.features.serie.data.model.SerieDto
import com.example.filmix.features.serie.data.model.SerieRemoteKeys

@Database(
    entities = [SerieDto::class, SerieRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class SerieDatabase : RoomDatabase() {

    abstract fun serieDao(): SerieDao
    abstract fun serieRemoteKeysDao(): SerieRemoteKeysDao

}
