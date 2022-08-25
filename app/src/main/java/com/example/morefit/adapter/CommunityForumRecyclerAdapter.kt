package com.example.morefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.morefit.databinding.ItemCommunityForumBinding
import com.example.morefit.model.communityForum.Forum

class CommunityForumRecyclerAdapter(
    private val listener: CommunityForumInterface,
    private val data : List<Forum>
) : RecyclerView.Adapter<CommunityForumRecyclerAdapter.ViewHolder>() {

    companion object {
        var isLiked = false
    }

    inner class ViewHolder(val binding: ItemCommunityForumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.likeBtn.setOnClickListener {
                isLiked = !isLiked

                val likeCount = binding.likeCount.text.toString().toInt()
                binding.likeCount.text =
                    if (isLiked) (likeCount + 1).toString() else (likeCount - 1).toString()
            }

            binding.commentsRecyclerView.adapter = CommentRecyclerAdapter()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemCommunityForumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            postTitle.text = data[position].title
            postContent.text = data[position].content
            postImg.load(data[position].image) {
                crossfade(true)
            }
            likeBtn.isSelected = isLiked == true
            likeCount.text = data[position].liked_by.size.toString()
        }
    }

    override fun getItemCount(): Int = data.size
}

interface CommunityForumInterface {
    fun likeBtnClickListener()
}