package com.example.movies.screen.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movies.R
import com.example.movies.adapter.MoviesAdapter
import com.example.movies.databinding.FragmentHomeBinding
import com.example.movies.util.MoviesType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
   lateinit var binding: FragmentHomeBinding
   private val viewModel by viewModels<HomeViewModel>()

    private val topMoviesAdapter = MoviesAdapter()
    private val popularMoviesAdapter = MoviesAdapter()
    private val upcomingMoviesAdapter = MoviesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTopMovies.adapter = topMoviesAdapter
        binding.rvPopular.adapter = popularMoviesAdapter
        binding.rvUpcoming.adapter = upcomingMoviesAdapter
        observeData()

        viewModel.getTopMovies()
        viewModel.getPopularMovies()
        viewModel.getUpcomingMovies()
        viewModel.getNowPlayingMovies()


        binding.textViewSeeTopMovies.setOnClickListener {
            val titleTop = binding.textViewTitleTop.text.toString()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MoviesType.Top,titleTop))
        }

        binding.textViewSeePopularMovies.setOnClickListener {
            val titlePopular = binding.textViewPopular.text.toString()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MoviesType.Popular, titlePopular))
        }

        binding.textViewSeeUpcomingMovies.setOnClickListener {
            val titleUpcoming = binding.textViewPopular.text.toString()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(MoviesType.Upcoming,titleUpcoming))
        }




    }


    private fun observeData(){
        viewModel.topMovies.observe(viewLifecycleOwner){
            topMoviesAdapter.updateList(it.take(5))
        }

        viewModel.popularMovies.observe(viewLifecycleOwner){
            popularMoviesAdapter.updateList(it.take(5))
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner){
            upcomingMoviesAdapter.updateList(it.take(5))
        }

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner){
            binding.movie = it.random()
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}