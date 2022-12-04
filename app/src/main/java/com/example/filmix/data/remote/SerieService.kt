package com.example.filmix.data.remote

import com.example.filmix.core.Constants
import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.core.Constants.DEFAULT_LANGUAGE
import com.example.filmix.data.model.films.FilmResponse
import com.example.filmix.data.model.series.SerieDetailsDto
import com.example.filmix.data.model.series.SerieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SerieService {

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): SerieResponse

    @GET("tv/{tv_id}")
    suspend fun getSerieDetails(
        @Path("tv_id") serieId: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): SerieDetailsDto

}