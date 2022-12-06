package com.example.filmix.presentation.filmFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.filmix.R
import com.example.filmix.databinding.FragmentFilmBinding
import com.example.filmix.presentation.adapters.FilmPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class FilmFragment : Fragment(R.layout.fragment_film) {

    lateinit var mActivity: FragmentActivity

    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FilmPagingAdapter
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
        initRecyclerView()
        updateList()
        updateTrending()
        toDetailsFragment()
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

    private fun toDetailsFragment() {
        adapter.onClickItem { filmId ->
            val action = FilmFragmentDirections.filmFragmentToDetailsFragment(filmId)
            findNavController().navigate(action)
        }
    }

//    private fun setUpToolbar() {
//        val mainActivity = mActivity as MainActivity
//
//        val toolbar = binding.toolbar
//        mainActivity.setSupportActionBar(toolbar)
//        val navController = NavHostFragment.findNavController(fragment = this)
//        val appBarConfiguration = mainActivity.appBarConfiguration
//        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}