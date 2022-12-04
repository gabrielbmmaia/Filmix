package com.example.filmix.presentation.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.filmix.domain.model.film.Film

class FilmPagingAdapter : PagingDataAdapter<Film,
        ViewHolder>(FILM_COMPARATOR) {

    companion object {
        private val FILM_COMPARATOR = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean =
                oldItem == newItem
        }
    }

    private var onClick: ((String) -> Unit)? = null
    fun onClickItem(listener: (String) -> Unit){
        onClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder.create(parent)
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