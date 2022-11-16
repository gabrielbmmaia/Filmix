package com.example.filmix.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmix.core.Constants.FILM_TABLE
import com.example.filmix.data.model.FilmDto

@Dao
interface FilmDao {

    @Query("SELECT * FROM $FILM_TABLE")
    fun getAllFilms(): PagingSource<Int,FilmDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFilms(films: List<FilmDto>)

    @Query("DELETE FROM $FILM_TABLE")
    suspend fun deleteAllFilms()
}