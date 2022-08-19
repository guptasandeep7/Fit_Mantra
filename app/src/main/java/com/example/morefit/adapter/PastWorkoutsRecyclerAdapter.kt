package com.example.morefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morefit.databinding.ItemPastWorkoutBinding

class PastWorkoutsRecyclerAdapter (val listener: PastWorkoutsInterface) :
	RecyclerView.Adapter<PastWorkoutsRecyclerAdapter.ViewHolder>(){
	inner class ViewHolder(val binding: ItemPastWorkoutBinding): RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ViewHolder(ItemPastWorkoutBinding.inflate(
			LayoutInflater.from(parent.context), parent, false))

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.binding.apply {

		}
	}

	override fun getItemCount() = 4
}

interface PastWorkoutsInterface {

}