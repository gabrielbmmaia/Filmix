package com.example.filmix.di

import android.util.Log
import com.example.filmix.core.Constants.BASE_URL
import com.example.filmix.core.Constants.OK_HTTP
import com.example.filmix.features.films.data.remote.FilmService
import com.example.filmix.features.serie.data.remote.SerieService
import com.example.filmix.features.trending.data.remote.TrendingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor {
            Log.e(OK_HTTP, it)
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesFilmService(retrofit: Retrofit): FilmService {
        return retrofit.create(FilmService::class.java)
    }

    @Provides
    @Singleton
    fun providesTrendingService(retrofit: Retrofit): TrendingService {
        return retrofit.create(TrendingService::class.java)
    }

    @Provides
    @Singleton
    fun providesSerieService(retrofit: Retrofit): SerieService {
        return retrofit.create(SerieService::class.java)
    }
}
