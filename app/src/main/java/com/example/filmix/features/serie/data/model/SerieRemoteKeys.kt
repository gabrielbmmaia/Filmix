package com.example.filmix.features.serie.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.filmix.core.Constants.SERIE_REMOTE_KEYS_TABLE

@Entity(tableName = SERIE_REMOTE_KEYS_TABLE)
data class SerieRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
