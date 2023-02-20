package com.example.filmix.core

import com.example.filmix.BuildConfig

object Constants {

    // Api Constants
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val BASE_POSTER_IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    const val OK_HTTP = "ok_http"
    const val DEFAULT_LANGUAGE = "en-US"
    const val SORT_BY_POPULARITY = "popularity.desc"
    const val FILM_MEDIA_TYPE = "movie"
    const val SERIE_MEDIA_TYPE = "tv"
    const val DAY_TIME_WINDOW = "day"
    const val WEEK_TIME_WINDOW = "week"
    const val DEFAULT_REGION = "BR"

    // Paging Constants
    const val PAGE_SIZE = 20

    // Log.e Constants
    const val USECASE_TAG = "use_case_error"
    const val PAGINGSOURCE_TAG = "paging_source_tag"
}
