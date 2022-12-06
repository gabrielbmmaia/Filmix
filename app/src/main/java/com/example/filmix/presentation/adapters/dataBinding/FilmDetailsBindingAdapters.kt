package com.example.filmix.presentation.adapters.dataBinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.filmix.core.Constants.BASE_POSTER_IMAGE_URL
import com.example.filmix.core.toBrazilianDate
import com.example.filmix.features.films.domain.model.FilmDetails

@BindingAdapter("loadPosterFromUrl")
fun ImageView.loadPosterFromUrl(filmDetails: FilmDetails?) {
    filmDetails?.posterPath?.let { posterUrl ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterUrl
        this.load(finalUrl)
    }
}

@BindingAdapter("filmDetailsTitle")
fun TextView.filmDetailsTitle(filmDetails: FilmDetails?) {
    filmDetails?.title?.let {
        text = it
    }
}

@BindingAdapter("filmDetailsOriginalTitle")
fun TextView.filmDetailsOriginalTitle(filmDetails: FilmDetails?) {
    filmDetails?.originalTitle?.let {
        text = it
    }
}

@BindingAdapter("filmDetailsReleaseDate")
fun TextView.filmDetailsReleaseDate(filmDetails: FilmDetails?) {
    filmDetails?.releaseDate?.let {
        text = it.toBrazilianDate()
    }
}

@BindingAdapter("filmDetailsOverview")
fun TextView.filmDetailsOverview(filmDetails: FilmDetails?) {
    filmDetails?.overview?.let {
        text = it
    }
}

@BindingAdapter("filmDetailsRating")
fun TextView.filmDetailsRating(filmDetails: FilmDetails?) {
    filmDetails?.voteAverage?.let {
        text = it.toString()
    }
}

@BindingAdapter("filmDetailsDuration")
fun TextView.filmDetailsDuration(filmDetails: FilmDetails?) {
    filmDetails?.runtime?.let {
        text = it.toString()
    }
}