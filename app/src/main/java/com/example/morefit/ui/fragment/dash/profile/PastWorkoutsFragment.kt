package com.example.morefit.ui.fragment.dash.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.morefit.R
import com.example.morefit.adapter.PastWorkoutsInterface
import com.example.morefit.adapter.PastWorkoutsRecyclerAdapter
import com.example.morefit.databinding.FragmentPastWorkoutsBinding
import com.google.android.material.transition.platform.MaterialSharedAxis

class PastWorkoutsFragment : Fragment(R.layout.fragment_past_workouts), PastWorkoutsInterface{
	private lateinit var binding: FragmentPastWorkoutsBinding
	private val adapter by lazy { PastWorkoutsRecyclerAdapter(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enterTransition = MaterialSharedAxis(MaterialSharedAxis.X,true)
		returnTransition = MaterialSharedAxis(MaterialSharedAxis.X,false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding = FragmentPastWorkoutsBinding.bind(view).apply {
			workoutsRecyclerView.adapter = adapter
			icBack.setOnClickListener { activity?.onBackPressed() }
		}
	}
}