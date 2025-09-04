package com.example.movies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemMoviesBinding
import com.example.movies.model.movies.Result
import com.example.movies.screen.home.HomeFragmentDirections

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){


    private val movieList = arrayListOf<Result>()


    class MoviesViewHolder(val itemMoviesBinding: ItemMoviesBinding):
        RecyclerView.ViewHolder(itemMoviesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movieList[position]
        holder.itemMoviesBinding.movie = item
        holder.itemMoviesBinding.cardViewMovies.setOnClickListener { view->
            item.id?.let {
                Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment2(item.id))
            }
        }

    }

    fun updateList(newList: List<Result>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}