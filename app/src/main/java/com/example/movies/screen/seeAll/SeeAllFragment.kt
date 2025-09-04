package com.example.movies.screen.seeAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movies.R
import com.example.movies.adapter.DiscoverMoviesAdapter
import com.example.movies.databinding.FragmentSeeAllBinding
import com.example.movies.util.MoviesType
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SeeAllFragment : Fragment() {
    lateinit var binding: FragmentSeeAllBinding
    private val adapter = DiscoverMoviesAdapter()
    private val args: SeeAllFragmentArgs by navArgs()
    private val viewModel by viewModels<SeeAllViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeeAllBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSeeAllMovies.adapter = adapter

        observeData()

        binding.textViewHeader.text = args.title
        viewModel.getMovies(args.type)
    }

    fun observeData(){
        viewModel.movies.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }

        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}