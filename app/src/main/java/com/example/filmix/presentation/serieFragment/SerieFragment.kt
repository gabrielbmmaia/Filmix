package com.example.filmix.presentation.serieFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.filmix.R
import com.example.filmix.databinding.FragmentSerieBinding
import com.example.filmix.presentation.adapters.SeriePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SerieFragment : Fragment(R.layout.fragment_serie) {

    private var _binding: FragmentSerieBinding? = null
    private val binding get() = _binding!!

    private val viewmodel by viewModels<SerieViewModel>()
    lateinit var adapter: SeriePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSerieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateTrending()
        initRecyclerView()
        updateList()
    }

    private fun updateTrending() {
        lifecycleScope.launchWhenStarted {
            viewmodel.trendingSerie.collectLatest { result ->
                when (result) {
                    TrendingSerieState.Empty -> {}
                    is TrendingSerieState.Error -> {}
                    TrendingSerieState.Loading -> {}
                    is TrendingSerieState.Success -> {
                        binding.trendingSerie = result.data
                    }
                }
            }
        }
    }

    private fun updateList() {
        lifecycleScope.launchWhenStarted {
            viewmodel.serieList.collectLatest { result ->
                when (result) {
                    SeriePagingState.Empty -> {}
                    is SeriePagingState.Error -> {}
                    SeriePagingState.Loading -> {}
                    is SeriePagingState.Success -> {
                        adapter.submitData(result.data)
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = SeriePagingAdapter()
        binding.serieRecyclewview.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
