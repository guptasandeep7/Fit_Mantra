package com.example.morefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.morefit.databinding.MealItemBinding
import com.example.morefit.model.item_model

class MealDinnerAdapter : RecyclerView.Adapter<MealDinnerAdapter.ViewHolder>() {

    var mealList = mutableListOf<item_model>()
    fun updateMealDinnerList(category: List<item_model>) {
        mealList.clear()
        this.mealList = category.toMutableList()
        notifyDataSetChanged()

    }


    class ViewHolder(val binding: MealItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(MealData: item_model) {
            binding.breakfastTitle.text = MealData.title
            binding.imageView3.load(MealData.image)
            binding.breakfastQuantity.text=MealData.serving
            binding.breakfastCal.text=MealData.cal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        (MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealList[position])
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

}