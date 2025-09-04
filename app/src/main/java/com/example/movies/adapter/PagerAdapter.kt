package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemPagerBinding
import com.example.movies.model.pager.PagerModel

class PagerAdapter: RecyclerView.Adapter<PagerAdapter.PagerViewHolder>() {

    val pagerList = arrayListOf<PagerModel>()


    class PagerViewHolder(val itemPagerBinding: ItemPagerBinding):
        RecyclerView.ViewHolder(itemPagerBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pagerList.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = pagerList[position]

        holder.itemPagerBinding.item = item
    }

    fun updateList(newList: List<PagerModel>){
        pagerList.clear()
        pagerList.addAll(newList)
        notifyDataSetChanged()
    }
}