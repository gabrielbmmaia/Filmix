package com.example.filmix.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmix.core.Constants.FILM_REMOTE_KEYS_TABLE

@Entity(tableName = FILM_REMOTE_KEYS_TABLE)
data class FilmRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
