package com.example.filmix.presentation.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.filmix.core.Constants.BASE_POSTER_IMAGE_URL
import com.example.filmix.domain.model.Film
import com.example.filmix.domain.model.FilmDetails

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(film: Film?) {
    film?.posterPath?.let { posterPath ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterPath
        this.load(finalUrl) {
            crossfade(enable = true)
            crossfade(durationMillis = 500)
        }
    }
}

@BindingAdapter("loadImageFromUrlNoFade")
fun ImageView.loadImageFromUrlNoFade(filmDetails: FilmDetails?) {
    filmDetails?.posterPath?.let { posterPath ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterPath
        this.load(finalUrl)
    }
}

@BindingAdapter("filmTitle")
fun TextView.filmTitle(film: Film?) {
    film?.let {
        text = it.title
    }
}

@BindingAdapter("filmSinopse")
fun TextView.filmSinopse(film: Film?) {
    film?.let {
        text = it.overview
    }
}

@BindingAdapter("filmRating")
fun TextView.filmRating(film: Film?) {
    film?.let {
        text = it.voteAverage.toString()
    }
}