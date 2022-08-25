package com.example.morefit.ui.fragment.dash.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.Fade
import com.example.morefit.R
import com.example.morefit.databinding.FragmentProfileBinding
import com.example.morefit.utils.Datastore
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialSharedAxis
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile){
	private lateinit var binding: FragmentProfileBinding
	private lateinit var datastore: Datastore

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		exitTransition = MaterialSharedAxis(MaterialSharedAxis.X,true)
		reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X,false)
		datastore = Datastore(requireContext())
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding = FragmentProfileBinding.bind(view).apply {
			profileProgress.show()
			profileProgressLight.show()
			historyBtn.setOnClickListener {
				findNavController().navigate(R.id.action_profileFragment_to_pastWorkoutsFragment)
			}
			lifecycleScope.launch {
				firstName.text = datastore.getUserDetails(Datastore.NAME_KEY).toString()
				lastName.text = datastore.getUserDetails(Datastore.LAST_NAME_KEY).toString()
			}
			icBack.setOnClickListener { activity?.onBackPressed() }
			GlobalScope.launch {
				streak.append("${datastore.getStreakCount()} Days")
			}
		}
	}
}