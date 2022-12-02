package com.example.filmix.data.remote

import com.example.filmix.core.Constants.API_KEY
import com.example.filmix.data.model.trending.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingItem(
        @Path("media_type") mediaType: String,
        @Path("time_window") timeWindow: String,
        @Query("api_key") key: String = API_KEY
    ): TrendingResponse
}
