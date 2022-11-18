package com.example.filmix.core

import com.example.filmix.BuildConfig

object Constants {

    // Api Constants
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w342"

    // Room Constants
    const val FILM_TABLE = "film_table"
    const val FILM_REMOTE_KEYS_TABLE = "film_remote_keys_table"
    const val FILM_DATABASE = "film_database"

    // Paging Constants
    const val FILM_PAGE_SIZE = 20

}