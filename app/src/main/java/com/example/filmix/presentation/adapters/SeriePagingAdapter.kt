package com.example.filmix.presentation.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filmix.features.serie.domain.model.Serie

class SeriePagingAdapter : PagingDataAdapter<Serie,
        RecyclerView.ViewHolder>(comparator) {

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<Serie>() {
            override fun areItemsTheSame(oldItem: Serie, newItem: Serie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Serie, newItem: Serie): Boolean =
                oldItem == newItem
        }
    }

    private var onClick: ((String) -> Unit)? = null
    fun onClickItem(listener: (String) -> Unit) {
        onClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        return SerieViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val serieItem = getItem(position)
        holder as SerieViewHolder
        serieItem?.let { serie ->
            holder.bind(serie)
            holder.binding.root.setOnClickListener {
                onClick?.let {
                    it(serie.id)
                }
            }
        }
    }
}
