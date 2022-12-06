package com.example.filmix.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.filmix.core.Constants.BASE_POSTER_IMAGE_URL
import com.example.filmix.core.invisible
import com.example.filmix.features.films.domain.model.Film
import com.example.filmix.features.films.domain.model.FilmDetails

@BindingAdapter("loadImageFromUrlNoFade")
fun ImageView.loadImageFromUrlNoFade(filmDetails: FilmDetails?) {
    filmDetails?.posterPath?.let { posterPath ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterPath
        this.load(finalUrl)
    }
}

@BindingAdapter("loadFilmGenres")
fun TextView.loadFilmGenres(filmDetails: FilmDetails?) {
    filmDetails?.genres?.let { genreList ->
        val genreNames = genreList.map { it.name }
        text = genreNames.joinToString(" • ")
    }
}
