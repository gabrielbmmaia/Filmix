package com.example.filmix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.filmix.databinding.MediaViewHolderItemBinding
import com.example.filmix.features.shared.domain.model.Media

class MediaPagingAdapter : PagingDataAdapter<Media, ViewHolder>(comparator) {

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<Media>() {
            override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean =
                oldItem == newItem
        }
    }

    class MediaViewHolder(val binding: MediaViewHolderItemBinding) : ViewHolder(binding.root) {
        fun bind(media: Media) {
            binding.media = media
        }
    }

    private var onClick: ((String) -> Unit)? = null
    fun onClickItem(listener: (String) -> Unit) {
        onClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MediaViewHolderItemBinding.inflate(inflater, parent, false)
        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filmItem = getItem(position)
        holder as MediaViewHolder
        filmItem?.let { media ->
            holder.bind(media)
            holder.binding.root.setOnClickListener {
                onClick?.let {
                    it(media.id)
                }
            }
        }
    }
}
