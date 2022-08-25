package com.example.morefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morefit.databinding.ItemCommunityForumCommentBinding

class CommentRecyclerAdapter: RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCommunityForumCommentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCommunityForumCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {

        }
    }

    override fun getItemCount(): Int = 4
}