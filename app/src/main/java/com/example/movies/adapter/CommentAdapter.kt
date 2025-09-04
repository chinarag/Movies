package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ItemCommentBinding
import com.example.movies.model.comment.Result

class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val commentList = arrayListOf<Result>()

    class CommentViewHolder(val itemCommentBinding: ItemCommentBinding):
        RecyclerView.ViewHolder(itemCommentBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = commentList[position]
        holder.itemCommentBinding.comment = item
    }

    fun updateList(newList: List<Result>){
        commentList.clear()
        commentList.addAll(newList)
        notifyDataSetChanged()

    }
}