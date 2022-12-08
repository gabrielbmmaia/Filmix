package com.example.filmix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.filmix.databinding.FilmViewHolderItemBinding
import com.example.filmix.features.films.domain.model.Film

class FilmPagingAdapter : PagingDataAdapter<Film, ViewHolder>(comparator) {

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean =
                oldItem == newItem
        }
    }

    class FilmViewHolder(val binding: FilmViewHolderItemBinding) : ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.film = film
        }
    }

    private var onClick: ((String) -> Unit)? = null
    fun onClickItem(listener: (String) -> Unit) {
        onClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmViewHolderItemBinding.inflate(inflater, parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmItem = getItem(position)
        holder as FilmViewHolder
        filmItem?.let { film ->
            holder.bind(film)
            holder.binding.root.setOnClickListener {
                onClick?.let {
                    it(film.id)
                }
            }
        }
    }
}
