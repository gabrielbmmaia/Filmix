package com.example.filmix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.filmix.databinding.SerieViewHolderItemBinding
import com.example.filmix.features.serie.domain.model.Serie

class SeriePagingAdapter : PagingDataAdapter<Serie, ViewHolder>(comparator) {

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<Serie>() {
            override fun areItemsTheSame(oldItem: Serie, newItem: Serie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Serie, newItem: Serie): Boolean =
                oldItem == newItem
        }
    }

    class SerieViewHolder(val binding: SerieViewHolderItemBinding) : ViewHolder(binding.root) {
        fun bind(serie: Serie) {
            binding.serie = serie
        }
    }

    private var onClick: ((String) -> Unit)? = null
    fun onClickItem(listener: (String) -> Unit) {
        onClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SerieViewHolderItemBinding.inflate(inflater, parent, false)
        return SerieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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
