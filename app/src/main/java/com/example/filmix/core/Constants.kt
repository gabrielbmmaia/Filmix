package com.example.filmix.core

import com.example.filmix.BuildConfig

object Constants {

    // Api Constants
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    const val OK_HTTP = "ok_http"
    const val DEFAULT_LANGUAGE = "en-US"
    const val FILM_MEDIA_TYPE = "movie"
    const val SERIE_MEDIA_TYPE = "tv"
    const val DAY_TIME_WINDOW = "day"

    // Room Constants
    const val FILM_TABLE = "film_table"
    const val FILM_REMOTE_KEYS_TABLE = "film_remote_keys_table"
    const val FILM_DATABASE = "film_database"
    const val SERIE_TABLE = "serie_table"
    const val SERIE_REMOTE_KEYS_TABLE = "serie_remote_keys_table"
    const val SERIE_DATABASE = "serie_database"

    // Paging Constants
    const val PAGE_SIZE = 20
    const val FILM_MAX_PAGE_SIZE = PAGE_SIZE * 3

    // Log.e Constants
    const val USECASE_TAG = "use_case_error"
    const val REMOTEMEDIATOR_TAG = "remote_mediator_tag"
}
