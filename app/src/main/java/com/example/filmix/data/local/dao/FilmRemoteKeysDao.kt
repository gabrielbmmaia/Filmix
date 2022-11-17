package com.example.filmix.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmix.core.Constants.FILM_REMOTE_KEYS_TABLE
import com.example.filmix.data.model.FilmRemoteKeys

@Dao
interface FilmRemoteKeysDao {

    @Query("SELECT * FROM $FILM_REMOTE_KEYS_TABLE WHERE id=:id")
    fun getRemoteKey(id: Int): FilmRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<FilmRemoteKeys>)

    @Query("DELETE FROM $FILM_REMOTE_KEYS_TABLE")
    suspend fun deleteAllRemoteKeys()
}