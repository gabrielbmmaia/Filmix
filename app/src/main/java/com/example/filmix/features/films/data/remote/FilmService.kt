package com.example.filmix.features.films.data.remote

import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.core.Constants.DEFAULT_LANGUAGE
import com.example.filmix.features.films.data.model.FilmDetailsDto
import com.example.filmix.features.films.data.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {

    @GET("movie/popular")
    suspend fun getPopularFilms(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmResponse

    @GET("search/movie")
    suspend fun searchFilms(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int,
        @Query("query") query: String
    ): FilmResponse

    @GET("movie/{movie_id}")
    suspend fun filmDetails(
        @Path("movie_id") filmId: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmDetailsDto
}
