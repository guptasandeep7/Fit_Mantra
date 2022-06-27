package com.example.morefit.ui.fragment.dash.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.Fade
import com.example.morefit.R
import com.example.morefit.databinding.FragmentProfileBinding
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialSharedAxis

class ProfileFragment : Fragment(R.layout.fragment_profile){
	private lateinit var binding: FragmentProfileBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		exitTransition = MaterialSharedAxis(MaterialSharedAxis.X,true)
		reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X,false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding = FragmentProfileBinding.bind(view).apply {
//			profileProgress.show()
			historyBtn.setOnClickListener {
				findNavController().navigate(R.id.action_profileFragment_to_pastWorkoutsFragment)
			}
		}
	}
}