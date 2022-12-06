package com.example.filmix.presentation.serieFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.filmix.R
import com.example.filmix.databinding.FragmentSerieBinding


class SerieFragment : Fragment(R.layout.fragment_serie) {

    private var _binding: FragmentSerieBinding? = null
    private val binding get() = _binding!!

    private val viewmodel by viewModels<SerieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSerieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}