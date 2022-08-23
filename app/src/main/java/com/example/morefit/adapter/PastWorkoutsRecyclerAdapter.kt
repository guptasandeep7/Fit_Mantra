package com.example.morefit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.morefit.databinding.ItemPastWorkoutBinding
import com.example.morefit.model.database.Content
import com.example.morefit.utils.day
import com.example.morefit.utils.formattedTimeDuration
import com.example.morefit.utils.month

class PastWorkoutsRecyclerAdapter(
    val listener: PastWorkoutsInterface,
    val data: List<Content>
) :
    RecyclerView.Adapter<PastWorkoutsRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPastWorkoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemPastWorkoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            month.text = data[position].date.month()
            day.text = data[position].date.day()
            exerciseName.text = data[position].name
            repCount.text = "${data[position].reps} reps\n${data[position].time.formattedTimeDuration()}"
        }
    }

    override fun getItemCount() = data.size
}

interface PastWorkoutsInterface {

}