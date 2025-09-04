package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.databinding.ItemMyListBinding
import com.example.movies.model.MyListModel
import com.example.movies.model.movies.Result

class MyListAdapter:RecyclerView.Adapter<MyListAdapter.MyListViewHolder>() {

     private val movieList = arrayListOf<MyListModel>()
    var selected = false

    lateinit var onItemClickListener: ((MyListModel) -> Unit)


    class MyListViewHolder(val itemMyListBinding: ItemMyListBinding):
        RecyclerView.ViewHolder(itemMyListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val view = ItemMyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        val item = movieList[position]
        holder.itemMyListBinding.movie = item



        holder.itemMyListBinding.imageViewCheck.setOnClickListener {
            onItemClickListener(item)
        }
    }

    fun updateList(newList: List<MyListModel>){
        movieList.clear()
        movieList.addAll(newList)
        notifyDataSetChanged()

    }


}