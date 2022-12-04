package com.example.filmix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmix.databinding.FilmViewHolderItemBinding
import com.example.filmix.domain.model.film.Film

class FilmViewHolder(val binding: FilmViewHolderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {
        binding.film = film
    }

    companion object {
        fun create(view: ViewGroup): FilmViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = FilmViewHolderItemBinding.inflate(inflater, view, false)
            return FilmViewHolder(binding)
        }
    }
}