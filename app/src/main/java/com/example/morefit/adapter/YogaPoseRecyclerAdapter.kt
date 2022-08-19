package com.example.morefit.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.example.morefit.R
import com.example.morefit.databinding.ItemYogaPoseBinding
import com.example.morefit.model.Pose

class YogaPoseRecyclerAdapter(
	private val listener: YogaPoseInterface,
	private val data: List<Pose>
) : RecyclerView.Adapter<YogaPoseRecyclerAdapter.ViewHolder>() {

	private var lastClickTime = 0L

	inner class ViewHolder(val binding: ItemYogaPoseBinding) :
		RecyclerView.ViewHolder(binding.root) {
		init {
			binding.exploreBtn.setOnClickListener {
				if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
					return@setOnClickListener
				}
				lastClickTime = SystemClock.elapsedRealtime()
				listener.onPoseClickListener(data[adapterPosition])
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
		ViewHolder(ItemYogaPoseBinding.inflate(
			LayoutInflater.from(parent.context), parent, false))

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.binding.apply {
			pose.text = "${data[position].english_name} Pose"
			sanskritPose.text = "Sanskrit \"${data[position].sanskrit_name}\""
			poseImg.load(data[position].image_url) {
				crossfade(true)
				placeholder(R.drawable.ic_yoga)
				error(R.drawable.ic_yoga)
				scale(Scale.FILL)
			}
		}
	}

	override fun getItemCount(): Int = data.size
}

interface YogaPoseInterface {
	fun onPoseClickListener(yogaPoseDetail: Pose)
}