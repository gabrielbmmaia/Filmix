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
import com.example.filmix.presentation.adapters.LoadStateAdapter
import com.example.filmix.presentation.adapters.MediaPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FilmFragment : Fragment(R.layout.fragment_film) {

    //Rever, possivelmente não está sendo utlizado
    lateinit var mActivity: FragmentActivity

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private lateinit var popularAdapter: MediaPagingAdapter
    private lateinit var ratedAdapter: MediaPagingAdapter
    private lateinit var soonAdapter: MediaPagingAdapter
    private lateinit var theatreAdapter: MediaPagingAdapter

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
        updateTrending()
        toDetailsFragment()
    }

    //Refatorar
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

    private fun initRecyclerViews() {
        initPopularRecyclerView()
        initRatedRecyclerView()
        initSoonRecyclerView()
        initTheatreRecyclerView()
    }

    private fun initPopularRecyclerView() {
        // Configing Adapter
        popularAdapter = MediaPagingAdapter()
        binding.popularFilmList.titleMediaList.text = getString(R.string.tag_popular)
        with(binding.popularFilmList.rvMediaList) {
            hasFixedSize()
            adapter = popularAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { popularAdapter.retry() }
            )
        }
        //Update RecyclerView
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
        // State Handling
        popularAdapter.addLoadStateListener { loadState ->
            with(binding.popularFilmList) {
                rvMediaList.isVisible =
                    loadState.source.refresh is LoadState.NotLoading
                retryButton.isVisible =
                    loadState.source.refresh is LoadState.Error
                loading.isVisible =
                    loadState.source.refresh is LoadState.Loading
                errorMessage.isVisible =
                    loadState.source.refresh is LoadState.Error
            }
        }
        binding.popularFilmList.retryButton.setOnClickListener {
            popularAdapter.retry()
        }
    }

    private fun initRatedRecyclerView() {
        // Configing Adapter
        ratedAdapter = MediaPagingAdapter()
        binding.topRatedFilmList.titleMediaList.text = getString(R.string.tag_top_rated)
        with(binding.topRatedFilmList.rvMediaList) {
            hasFixedSize()
            adapter = ratedAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { ratedAdapter.retry() }
            )
        }
        //Update RecyclerView
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
        // State Handling
        ratedAdapter.addLoadStateListener { loadState ->
            with(binding.topRatedFilmList) {
                rvMediaList.isVisible =
                    loadState.source.refresh is LoadState.NotLoading
                retryButton.isVisible =
                    loadState.source.refresh is LoadState.Error
                loading.isVisible =
                    loadState.source.refresh is LoadState.Loading
                errorMessage.isVisible =
                    loadState.source.refresh is LoadState.Error
            }
        }
        binding.topRatedFilmList.retryButton.setOnClickListener {
            ratedAdapter.retry()
        }
    }

    private fun initSoonRecyclerView() {
        // Configing Adapter
        soonAdapter = MediaPagingAdapter()
        binding.soonFilmList.titleMediaList.text = getString(R.string.tag_soon)
        with(binding.soonFilmList.rvMediaList) {
            hasFixedSize()
            adapter = soonAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { soonAdapter.retry() }
            )
        }
        //Update RecyclerView
        lifecycleScope.launchWhenStarted {
            viewModel.filmSoonList.collectLatest { result ->
                when (result) {
                    is FilmPagingState.Success -> {
                        soonAdapter.submitData(result.data)
                    }
                    else -> {}
                }
            }
        }
        // State Handling
        soonAdapter.addLoadStateListener { loadState ->
            with(binding.soonFilmList) {
                rvMediaList.isVisible =
                    loadState.source.refresh is LoadState.NotLoading
                retryButton.isVisible =
                    loadState.source.refresh is LoadState.Error
                loading.isVisible =
                    loadState.source.refresh is LoadState.Loading
                errorMessage.isVisible =
                    loadState.source.refresh is LoadState.Error
            }
        }
        binding.soonFilmList.retryButton.setOnClickListener {
            soonAdapter.retry()
        }
    }

    private fun initTheatreRecyclerView() {
        // Configing Adapter
        theatreAdapter = MediaPagingAdapter()
        binding.theatresFilmList.titleMediaList.text = getString(R.string.tag_theatres)
        with(binding.theatresFilmList.rvMediaList) {
            hasFixedSize()
            adapter = theatreAdapter.withLoadStateFooter(
                footer = LoadStateAdapter { theatreAdapter.retry() }
            )
        }
        //Update RecyclerView
        lifecycleScope.launchWhenStarted {
            viewModel.filmTheatreList.collectLatest { result ->
                when (result) {
                    is FilmPagingState.Success -> {
                        theatreAdapter.submitData(result.data)
                    }
                    else -> {}
                }
            }
        }
        // State Handling
        theatreAdapter.addLoadStateListener { loadState ->
            with(binding.theatresFilmList) {
                rvMediaList.isVisible =
                    loadState.source.refresh is LoadState.NotLoading
                retryButton.isVisible =
                    loadState.source.refresh is LoadState.Error
                loading.isVisible =
                    loadState.source.refresh is LoadState.Loading
                errorMessage.isVisible =
                    loadState.source.refresh is LoadState.Error
            }
        }
        binding.theatresFilmList.retryButton.setOnClickListener {
            theatreAdapter.retry()
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