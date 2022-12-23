package com.example.filmix.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filmix.databinding.LoadStateItemBinding
import com.example.filmix.presentation.adapters.LoadStateAdapter.LoadStateViewHolder

class LoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    class LoadStateViewHolder(
        val binding: LoadStateItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadstate: LoadState) {
            binding.progressBar.isVisible = loadstate is LoadState.Loading
            binding.errorMessage.isVisible = loadstate !is LoadState.Loading
            binding.retryButton.isVisible = loadstate !is LoadState.Loading
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = LoadStateItemBinding.inflate(inflater, parent, false)
                return LoadStateViewHolder(binding, retry)
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent, retry)
    }
}