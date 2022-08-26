package com.example.morefit.ui.fragment.dash.profile

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentProfileBinding
import com.example.morefit.utils.Datastore
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.roundToInt

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

			calculateBtn.setOnClickListener {
				findNavController().navigate(R.id.action_profileFragment_to_calculateBottomSheetFragment)
			}
			
			extraFeaturesBtn.setOnClickListener { 
				showDialog()
			}
		}
	}

	private fun showDialog() {
		val dialog = Dialog(requireContext())
		val layout = layoutInflater.inflate(R.layout.dialog_body_stats, null)
		dialog.setContentView(layout)
		dialog.setCancelable(true)
		val height = layout.findViewById<TextView>(R.id.height)
		val weight = layout.findViewById<TextView>(R.id.weight)
		val age = layout.findViewById<TextView>(R.id.age)
		val bmi = layout.findViewById<TextView>(R.id.bmi)

		lifecycleScope.launch {
			height.text= datastore.getUserDetails("height").toString()
			weight.text= datastore.getUserDetails("weight").toString()
			val weight = weight.text.toString().toDouble()
			val height = height.text.toString().toDouble()/100
			val bmivalue = (weight / height.pow(2.0)).roundToInt() * 100.0 / 100.0
			bmi.text=bmivalue.toString()
			age.text= datastore.getUserDetails("age").toString()
			dialog.show()
		}

	}
}