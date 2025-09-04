package com.example.movies.screen.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movies.R
import com.example.movies.adapter.DiscoverMoviesAdapter
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreFragment : Fragment() {
    lateinit var binding: FragmentExploreBinding
    private val moviesAdapter = DiscoverMoviesAdapter()
    private val viewModel by viewModels<ExploreViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAllMovies.adapter = moviesAdapter
        observeData()
        viewModel.getDiscoverMovies()

        binding.editTextSearch.addTextChangedListener {
            lifecycleScope.launch {
                if(it.toString().isNotEmpty()){
                    viewModel.search(it.toString())
                    delay(400)
                }else {
                    viewModel.getDiscoverMovies()
                }
            }
        }

    }


    private fun observeData(){
        viewModel.allMovies.observe(viewLifecycleOwner){
            moviesAdapter.updateList(it)
        }

        viewModel.searchMovies.observe(viewLifecycleOwner){
            moviesAdapter.updateList(it)
        }


        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

}