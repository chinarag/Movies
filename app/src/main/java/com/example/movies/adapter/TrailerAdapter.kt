package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemTrailerBinding
import com.example.movies.model.trailer.Result

class TrailerAdapter: RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder>() {
    private val trailerList = arrayListOf<Result>()

    var onItemClick: ((Result) -> Unit)? = null

    class TrailerViewHolder(val itemTrailerBinding: ItemTrailerBinding):
        RecyclerView.ViewHolder(itemTrailerBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return TrailerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val item = trailerList[position]
        holder.itemTrailerBinding.trailer = item
        holder.itemTrailerBinding.cvTrailer.setOnClickListener {
            onItemClick?.invoke(item)
        }

    }

    fun updateList(newList: List<Result>){
        trailerList.clear()
        trailerList.addAll(newList)
        notifyDataSetChanged()
    }
}