package com.example.filmix.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.filmix.core.Constants.BASE_POSTER_IMAGE_URL
import com.example.filmix.features.films.domain.model.FilmDetails
import com.example.filmix.features.serie.domain.model.SerieDetails

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(filmDetails: FilmDetails?) {
    filmDetails?.posterPath?.let { posterPath ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterPath
        this.load(finalUrl) {
            crossfade(enable = true)
            crossfade(durationMillis = 500)
        }
    }
}

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(serieDetails: SerieDetails?) {
    serieDetails?.posterPath?.let { posterPath ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterPath
        this.load(finalUrl) {
            crossfade(enable = true)
            crossfade(durationMillis = 500)
        }
    }
}

@BindingAdapter("loadItemGenres")
fun TextView.loadItemGenres(filmDetails: FilmDetails?) {
    filmDetails?.genres?.let { genreList ->
        val genreNames = genreList.map { it.name }
        text = genreNames.joinToString(" • ")
    }
}

@BindingAdapter("loadItemGenres")
fun TextView.loadItemGenres(serieDetails: SerieDetails?) {
    serieDetails?.genres?.let { genreList ->
        val genreNames = genreList.map { it.name }
        text = genreNames.joinToString(" • ")
    }
}