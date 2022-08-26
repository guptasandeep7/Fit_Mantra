package com.example.morefit.adapter

import android.graphics.Color
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.morefit.R
import com.example.morefit.databinding.ItemCommunityForumBinding
import com.example.morefit.model.communityForum.Comment
import com.example.morefit.model.communityForum.Forum

class CommunityForumRecyclerAdapter(
    private val listener: CommunityForumInterface,
    private val postData: List<Forum>,
    private val commentData : List<Comment>
) : RecyclerView.Adapter<CommunityForumRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCommunityForumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.likeBtn.setOnClickListener {
                listener.likeBtnClickListener(
                    postData[adapterPosition].id,
                    postData[adapterPosition].account
                )
                if (binding.likeBtn.isChecked) binding.likeCount.text =
                    (binding.likeCount.text.toString().toInt() + 1).toString()
                else binding.likeCount.text = (binding.likeCount.text.toString().toInt() - 1).toString()

                if (binding.likeBtn.isChecked) binding.likeCount.setTextColor(Color.parseColor("#F58426"))
                else binding.likeCount.setTextColor(Color.parseColor("#000000"))
            }

            binding.commentsRecyclerView.adapter = CommentRecyclerAdapter(commentData)

            binding.commentBtn.setOnClickListener {
                binding.commentsRecyclerView.visibility = if (binding.commentsRecyclerView.visibility != View.VISIBLE)
                     View.VISIBLE else View.GONE
            }
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
            postTitle.text = postData[position].title
            postContent.text = postData[position].content
            postImg.load(postData[position].image) {
                crossfade(true)
            }
            likeBtn.isChecked = postData[position].is_liked == true

            likeCount.text = postData[position].liked_by.size.toString()

            commentCount.text = commentData.size.toString()
        }
    }

    override fun getItemCount(): Int = postData.size
}

interface CommunityForumInterface {
    fun likeBtnClickListener(id: Int, account: Int)
}