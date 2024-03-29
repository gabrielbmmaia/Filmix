package com.example.filmix.presentation.adapters.dataBinding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.filmix.core.Constants.BASE_POSTER_IMAGE_URL
import com.example.filmix.core.invisible
import com.example.filmix.features.shared.domain.model.Media
import com.example.filmix.features.serie.domain.model.Serie

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(media: Media?) {
    media?.posterPath?.let { posterPath ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterPath
        this.load(finalUrl) {
            crossfade(enable = true)
            crossfade(durationMillis = 500)
        }
    }
}

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(serie: Serie?) {
    serie?.posterPath?.let { posterPath ->
        val finalUrl = BASE_POSTER_IMAGE_URL + posterPath
        this.load(finalUrl) {
            crossfade(enable = true)
            crossfade(durationMillis = 500)
        }
    }
}

@BindingAdapter("itemTitle")
fun TextView.itemTitle(media: Media?) {
    media?.title?.let {
        text = it
    }
}

@BindingAdapter("itemTitle")
fun TextView.itemTitle(serie: Serie?) {
    serie?.title?.let {
        text = it
    }
}

@BindingAdapter("itemRating")
fun TextView.itemRating(media: Media?) {
    media?.voteAverage?.let { rate ->
        if (rate >= 3.0) {
            text = rate.toString()
        } else invisible()
    }
}

@BindingAdapter("itemRating")
fun TextView.itemRating(serie: Serie?) {
    serie?.voteAverage?.let { rate ->
        if (rate >= 3.0) {
            text = rate.toString()
        } else invisible()
    }
}