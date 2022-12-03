package com.example.filmix.data.remote

import com.example.filmix.core.Constants
import com.example.filmix.core.Constants.DEFAULT_LANGUAGE
import com.example.filmix.data.model.films.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SerieService {

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") key: String = Constants.API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmResponse


}