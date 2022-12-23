package com.example.filmix.presentation.filmFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.filmix.R
import com.example.filmix.databinding.FragmentFilmBinding
import com.example.filmix.presentation.adapters.FilmPagingAdapter
import com.example.filmix.presentation.adapters.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FilmFragment : Fragment(R.layout.fragment_film) {

    lateinit var mActivity: FragmentActivity

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularAdapter: FilmPagingAdapter
    private lateinit var ratedAdapter: FilmPagingAdapter

    private val viewModel by viewModels<FilmViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.let { mActivity = it }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViews()
        updatePopularList()
        updateTrending()
        toDetailsFragment()
        updateRatedList()
    }

    private fun updateTrending() {
        lifecycleScope.launchWhenStarted {
            viewModel.trendingFilm.collectLatest { result ->
                when (result) {
                    is TrendingFilmState.Error -> {}
                    TrendingFilmState.Loading -> {}
                    is TrendingFilmState.Success -> {
                        binding.trendingFilm = result.data
                    }
                    else -> {}
                }
            }
        }
    }

    private fun updatePopularList() {
        lifecycleScope.launchWhenStarted {
            viewModel.filmPopularList.collectLatest { result ->
                when (result) {

                    is FilmPagingState.Success -> {
                        popularAdapter.submitData(result.data)

                    }
                    else -> {}
                }
            }
        }
    }

    private fun updateRatedList() {
        lifecycleScope.launchWhenStarted {
            viewModel.filmRatedList.collectLatest { result ->
                when (result) {

                    is FilmPagingState.Success -> {
                        ratedAdapter.submitData(result.data)

                    }
                    else -> {}
                }
            }
        }
    }

    private fun initRecyclerViews() {
        initPopularRecyclerView()

        ratedAdapter = FilmPagingAdapter()
        binding.rvTopRatedFilmList.hasFixedSize()
        binding.rvTopRatedFilmList.adapter = ratedAdapter
    }

    private fun initPopularRecyclerView() {
        // Configing Adapter
        popularAdapter = FilmPagingAdapter()
        binding.rvPopularFilmList.hasFixedSize()
        binding.rvPopularFilmList.adapter = popularAdapter.withLoadStateFooter(
            footer = LoadStateAdapter { popularAdapter.retry() }
        )
        // State Handling
        popularAdapter.addLoadStateListener { loadState ->
            binding.rvPopularFilmList.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.retryPopularFilmList.isVisible = loadState.source.refresh is LoadState.Error
            binding.loadingPopularFilmList.isVisible = loadState.source.refresh is LoadState.Loading
            binding.errorMessagePopularFilmList.isVisible = loadState.source.refresh is LoadState.Error
        }
        binding.retryPopularFilmList.setOnClickListener {
            popularAdapter.retry()
        }
    }

    private fun toDetailsFragment() {
        popularAdapter.onClickItem { filmId ->
            val action = FilmFragmentDirections.filmFragmentToDetailsFragment(filmId)
            findNavController().navigate(action)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}