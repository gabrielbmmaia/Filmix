package com.example.filmix.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.filmix.R
import com.example.filmix.databinding.FragmentHomeBinding
import com.example.filmix.presentation.adapters.FilmPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FilmPagingAdapter
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        updateList()
        updateTrending()
        toDetailsFragment()
    }

    private fun updateTrending(){
        lifecycleScope.launchWhenStarted {
            viewModel.trendingFilm.collectLatest { result ->
                when(result) {
                    is TrendingState.Error -> {}
                    TrendingState.Loading -> {}
                    is TrendingState.Success -> TODO()
                    else -> {}
                }
            }
        }
    }

    private fun updateList() {
        lifecycleScope.launchWhenStarted {
            viewModel.filmList.collectLatest { result ->
                when (result) {

                    is FilmPagingState.Success -> {
                        adapter.submitData(result.data)

                    }
                    else -> {}
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = FilmPagingAdapter()
        binding.homeRecyclewview.adapter = adapter
    }

    private fun toDetailsFragment(){
        adapter.onClickItem { filmId ->
            val action = HomeFragmentDirections.homeFragmentToDetailsFragment(filmId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}