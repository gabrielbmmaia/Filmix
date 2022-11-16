package com.example.filmix.data.remote

import com.example.filmix.core.Constants
import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.data.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmService {

    @GET("movie/popular")
    suspend fun getPopularFilms(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page:Int
    ): FilmResponse

}