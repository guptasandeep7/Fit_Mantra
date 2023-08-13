package com.example.morefit.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import com.example.morefit.R
import com.example.morefit.databinding.ItemChatBinding

class ChatRecyclerAdapter(
    private val context: Context
) : RecyclerView.Adapter<ChatRecyclerAdapter.ChatViewHolder>() {

    private val data = mutableListOf<String>("_")

    inner class ChatViewHolder(private val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            if (data.size == 1) {
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.dotLayout.isVisible = false
                    binding.textView22.isVisible = true
                    data[0] = "Hey there! I'm your personal fitness AI assistant 😀"
                    binding.textView22.text = data[0]
                    notifyDataSetChanged()
                }, 600)

            }

            if (data.size == 2) {
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.dotLayout.isVisible = false
                    binding.textView22.isVisible = true
                    binding.textView22.text = data[1]
                    binding.parentLayout.background =
                        ContextCompat.getDrawable(context, R.drawable.chat_right_bg)
                    binding.textView22.setTextColor(ContextCompat.getColor(context, R.color.white))

                    val layoutParams = binding.parentLayout.layoutParams as RecyclerView.LayoutParams
                    layoutParams.marginStart = 500

                    binding.parentLayout.layoutParams = layoutParams

                    data.add(
                        "The short answer is no, the keto diet is not safe during pregnancy.\n\n" +
                                "Ketosis is a state in which the body burns fat for energy instead of carbohydrates. This can be a helpful way to lose weight for people who are not pregnant, but it can be harmful for pregnant women and their babies."
                    )
                    notifyDataSetChanged()
                }, 100)
            }

            if (data.size == 3) {
                Handler(Looper.getMainLooper()).postDelayed({
                    binding.dotLayout.isVisible = false
                    binding.textView22.isVisible = true
                    binding.textView22.text = data[2]
                    notifyDataSetChanged()
                }, 1800)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ItemChatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
    }

    fun addMsg(msg: String = "") {
        data.add(msg)
        notifyDataSetChanged()
    }
}