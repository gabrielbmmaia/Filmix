package com.example.filmix.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmix.data.model.series.SerieRemoteKeys

@Dao
interface SerieRemoteKeysDao {

    @Query("SELECT * FROM serie_remote_keys_table WHERE id=:id")
    suspend fun getRemoteKey(id: String): SerieRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<SerieRemoteKeys>)

    @Query("DELETE FROM serie_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}
