package com.example.morefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.morefit.databinding.ExerciseItemBinding
import com.example.morefit.databinding.MealItemBinding
import com.example.morefit.model.Data
import com.example.morefit.model.item_model

class MealBreakAdapter : RecyclerView.Adapter<MealBreakAdapter.ViewHolder>() {

    var mealList = mutableListOf<item_model>()
    fun updateMealList(category: List<item_model>) {
        mealList.clear()
        this.mealList = category.toMutableList()
        notifyDataSetChanged()
    }

    private var mlistner: onItemClickListener? = null

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mlistner = listener
    }

    class ViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(MealData: item_model) {
            binding.breakfastTitle.text = MealData.title
            binding.imageView3.load(MealData.image)
            binding.breakfastQuantity.text=MealData.serving
            binding.breakfastCal.text=MealData.cal
//            binding.text.text = ""
//            address.text_tutorials.forEachIndexed { index, textTutorial ->
//                binding.text.append("${index + 1}) ${textTutorial.text}\n\n")
//            }
        }
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding: MealItemBinding = inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.meal_item, parent, false
//        )
//        return ViewHolder(binding, mlistner!!)
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        (MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealList[position])

//        holder.binding.imageView2.setOnClickListener{
//            mlistner?.onItemClick(position)
//        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

}