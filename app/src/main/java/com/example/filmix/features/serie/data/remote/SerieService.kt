package com.example.filmix.features.serie.data.remote

import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.core.Constants.DEFAULT_LANGUAGE
import com.example.filmix.core.Constants.DEFAULT_REGION
import com.example.filmix.features.serie.data.model.SerieDetailsDto
import com.example.filmix.features.serie.data.model.SerieResponse
import com.example.filmix.features.shared.data.model.MediaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SerieService {

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE,
    ): MediaResponse

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("api_key") key: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") language: String = DEFAULT_LANGUAGE,
    ): MediaResponse

    @GET("tv/{tv_id}")
    suspend fun getSerieDetails(
        @Path("tv_id") serieId: Int,
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = DEFAULT_LANGUAGE
    ): SerieDetailsDto

}
