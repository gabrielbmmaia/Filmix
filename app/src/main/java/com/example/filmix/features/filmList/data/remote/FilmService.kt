package com.example.filmix.features.filmList.data.remote

import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.core.Constants.DEFAULT_LANGUAGE
import com.example.filmix.core.Constants.DEFAULT_REGION
import com.example.filmix.core.Constants.FILM_MEDIA_TYPE
import com.example.filmix.core.Constants.WEEK_TIME_WINDOW
import com.example.filmix.features.filmList.data.model.FilmDetailsDto
import com.example.filmix.features.shared.data.model.MediaResponse
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
    ): MediaResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedFilms(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): MediaResponse

    @GET("movie/upcoming")
    suspend fun getSoonFilms(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("region") region: String = DEFAULT_REGION
    ): MediaResponse

    @GET("movie/now_playing")
    suspend fun getTheatresFilms(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("region") region: String = DEFAULT_REGION
    ): MediaResponse

    @GET("movie/{movie_id}")
    suspend fun filmDetails(
        @Path("movie_id") filmId: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmDetailsDto
}
