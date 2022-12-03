package com.example.filmix.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmix.data.model.series.SerieDto

@Dao
interface SerieDao {

    @Query("SELECT * FROM serie_table")
    fun getAllSeries(): PagingSource<Int, SerieDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSeries(series: List<SerieDto>)

    @Query("DELETE FROM serie_table")
    suspend fun deleteAllSeries()
}