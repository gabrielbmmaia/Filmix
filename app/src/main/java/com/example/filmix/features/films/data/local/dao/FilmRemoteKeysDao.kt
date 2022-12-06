package com.example.filmix.features.films.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmix.features.films.data.model.FilmRemoteKeys

@Dao
interface FilmRemoteKeysDao {

    @Query("SELECT * FROM film_remote_keys_table WHERE id=:id")
    suspend fun getRemoteKey(id: String): FilmRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<FilmRemoteKeys>)

    @Query("DELETE FROM film_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}
