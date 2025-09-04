package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemDiscoverMoviesBinding
import com.example.movies.databinding.ItemMoviesBinding
import com.example.movies.model.movies.Result

class DiscoverMoviesAdapter: RecyclerView.Adapter<DiscoverMoviesAdapter.DiscoverMoviesViewHolder>(){


    private val movieList = arrayListOf<Result>()


    class DiscoverMoviesViewHolder(val itemDiscoverMoviesBinding: ItemDiscoverMoviesBinding):
        RecyclerView.ViewHolder(itemDiscoverMoviesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverMoviesViewHolder {
        val view = ItemDiscoverMoviesBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return DiscoverMoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: DiscoverMoviesViewHolder, position: Int) {
        val item = movieList[position]
        holder.itemDiscoverMoviesBinding.movie = item

    }

    fun updateList(newList: List<Result>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()
    }
}