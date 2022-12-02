package com.example.filmix.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmix.data.model.films.FilmDto

@Dao
interface FilmDao {

    @Query("SELECT * FROM film_table")
    fun getAllFilms(): PagingSource<Int, FilmDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFilms(films: List<FilmDto>)

    @Query("DELETE FROM film_table")
    suspend fun deleteAllFilms()
}