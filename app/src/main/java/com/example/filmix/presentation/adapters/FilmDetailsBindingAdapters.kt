package com.example.filmix.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.filmix.core.Constants.BASE_POSTER_IMAGE_URL
import com.example.filmix.domain.model.FilmDetails

@BindingAdapter("loadPosterFromUrl")
fun ImageView.loadPosterFromUrl(filmDetails: FilmDetails) {
    filmDetails.posterPath?.let { posterUrl ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterUrl
        this.load(finalUrl)
    }
}

@BindingAdapter("filmDetailsTitle")
fun TextView.filmDetailsTitle(filmDetails: FilmDetails) {
    text = filmDetails.title
}

@BindingAdapter("filmDetailsOriginalTitle")
fun TextView.filmDetailsOriginalTitle(filmDetails: FilmDetails) {
    text = filmDetails.originalTitle
}

@BindingAdapter("filmDetailsReleaseDate")
fun TextView.filmDetailsReleaseDate(filmDetails: FilmDetails) {
    text = filmDetails.releaseDate
}

@BindingAdapter("filmDetailsOverview")
fun TextView.filmDetailsOverview(filmDetails: FilmDetails) {
    filmDetails.overview?.let { overview ->
        text = overview
    }
}

@BindingAdapter("filmDetailsTagLine")
fun TextView.filmDetailsTagLine(filmDetails: FilmDetails) {
    text = filmDetails.tagline
}