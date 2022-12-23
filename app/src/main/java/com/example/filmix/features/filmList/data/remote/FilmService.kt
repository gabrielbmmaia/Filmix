package com.example.filmix.features.filmList.data.remote

import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.core.Constants.DEFAULT_LANGUAGE
import com.example.filmix.core.Constants.FILM_MEDIA_TYPE
import com.example.filmix.core.Constants.SORT_BY_POPULARITY
import com.example.filmix.core.Constants.WEEK_TIME_WINDOW
import com.example.filmix.features.filmList.data.model.FilmDetailsDto
import com.example.filmix.features.filmList.data.model.FilmResponse
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

    @GET("movie/top_rated")
    suspend fun getTopRatedFilms(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmResponse

    @GET("discover/movie")
    suspend fun getPopularFilmsNetflix(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE,
        @Query("sort_by") sortType: String = SORT_BY_POPULARITY,
        @Query("with_companies") company: String
    ): FilmResponse

    @GET("movie/{movie_id}")
    suspend fun filmDetails(
        @Path("movie_id") filmId: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): FilmDetailsDto
}
