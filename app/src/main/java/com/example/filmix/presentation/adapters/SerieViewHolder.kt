package com.example.filmix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmix.databinding.SerieViewHolderItemBinding
import com.example.filmix.features.serie.domain.model.Serie

class SerieViewHolder(val binding: SerieViewHolderItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(serie: Serie) {
        binding.serie = serie
    }

    companion object {
        fun create(view: ViewGroup): SerieViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = SerieViewHolderItemBinding.inflate(inflater, view, false)
            return SerieViewHolder(binding)
        }
    }
}
