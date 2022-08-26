package com.example.morefit.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.morefit.R
import com.example.morefit.databinding.ExerciseItemBinding
import com.example.morefit.model.Data
import com.example.morefit.model.TextTutorial
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.io.Resources

class ExerciseAdapter : RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {

    var addressList = mutableListOf<Data>()
    fun updateAddressList(category: List<Data>) {
        addressList.clear()
        this.addressList = category.toMutableList()
        notifyDataSetChanged()
    }

    private var mlistner: onItemClickListener? = null

    interface onItemClickListener {
        fun onItemClick(position: Int)
        fun onActivityCLick(position: Int)
        fun onExerciseClickListener(tutorial: Data)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistner = listener
    }

    inner class ViewHolder(val binding: ExerciseItemBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.cardView.setOnClickListener {
                listener.onExerciseClickListener(addressList[adapterPosition])
            }
        }

        fun bind(data: Data) {
            binding.item = data
//            binding.text.text = ""
//            data.text_tutorials.forEachIndexed { index, textTutorial ->
//                binding.text.append("${index + 1}) ${textTutorial.text}\n\n")
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ExerciseItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.exercise_item, parent, false
        )
        return ViewHolder(binding, mlistner!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(addressList[position])
        holder.binding.button.setOnClickListener {
            mlistner?.onActivityCLick(position)
        }
        if (addressList[position].file_name.isNullOrEmpty()) {
            holder.binding.button.visibility = View.GONE
        }

        holder.binding.imageView2.setOnClickListener {
            mlistner?.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return addressList.size
    }

}