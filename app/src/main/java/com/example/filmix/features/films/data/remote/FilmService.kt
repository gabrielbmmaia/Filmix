package com.example.filmix.features.films.data.remote

import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.core.Constants.DEFAULT_LANGUAGE
import com.example.filmix.core.Constants.FILM_MEDIA_TYPE
import com.example.filmix.core.Constants.WEEK_TIME_WINDOW
import com.example.filmix.features.films.data.model.FilmDetailsDto
import com.example.filmix.features.films.data.model.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getPopularFilms(
        @Path("media_type") mediaType: String = FILM_MEDIA_TYPE,
        @Path("time_window") timeWindow: String = WEEK_TIME_WINDOW,
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmResponse

    @GET("movie/{movie_id}")
    suspend fun filmDetails(
        @Path("movie_id") filmId: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmDetailsDto
}
