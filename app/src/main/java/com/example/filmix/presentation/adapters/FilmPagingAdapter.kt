package com.example.filmix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.filmix.BR
import com.example.filmix.databinding.FilmViewHolderItemBinding
import com.example.filmix.domain.model.Film

class FilmPagingAdapter : PagingDataAdapter<Film,
        FilmPagingAdapter.MyViewHolder>(FilmDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            FilmViewHolderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.setVariable(BR.film, getItem(position))
    }

    class MyViewHolder(val binding: FilmViewHolderItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    private class FilmDiffCallBack : DiffUtil.ItemCallback<Film>() {
        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }
    }
}