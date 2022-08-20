package com.example.morefit.ui.fragment.dash.yoga

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import coil.load
import coil.size.Scale
import com.example.morefit.R
import com.example.morefit.databinding.FragmentYogaPoseDetailsBinding
import com.example.morefit.ui.activity.MlActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.platform.MaterialSharedAxis
import org.tensorflow.lite.examples.poseestimation.ml.PoseClassifier
import java.io.FileOutputStream

class YogaPoseDetailsFragment : Fragment(R.layout.fragment_yoga_pose_details) {
	private lateinit var binding: FragmentYogaPoseDetailsBinding

	override fun onStart() {
		super.onStart()
		val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
		bottomNavigationView.visibility = View.GONE
	}

	override fun onStop() {
		super.onStop()
		val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
		bottomNavigationView.visibility = View.VISIBLE
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
		returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding = FragmentYogaPoseDetailsBinding.bind(view).apply {
			val yogaPose = YogaPoseDetailsFragmentArgs.fromBundle(requireArguments()).yogaPose
			englishName.text = "${yogaPose.english_name} Pose"
			sanskritName.text = "Sanskrit \"${yogaPose.sanskrit_name}\""
			yogaDescription.text = yogaPose.yoga_description
			if(yogaPose.file_name.isNullOrEmpty())
			{
//				Toast.makeText(requireContext(), yogaPose.file_name.toString(), Toast.LENGTH_SHORT).show()
				PerformAsana.visibility=View.INVISIBLE
			}
			poseImg.load(yogaPose.image_url) {
				crossfade(true)
				crossfade(300)
				placeholder(R.drawable.ic_yoga)
				error(R.drawable.ic_yoga)
				scale(Scale.FILL)
			}
			PerformAsana.setOnClickListener {
				PoseClassifier.MODEL_FILENAME=yogaPose.file_name
				MlActivity.correct_label=yogaPose.correct_label
//				val filename="labels.txt"
//				val file: FileOutputStream = FileOutputStream(filename)
//				var string=""
//				for(i in yogaPose.labels)
//				{
//					string+=i
//					string+="\n"
//				}
//				file.write(string.toByteArray())
				val intent = Intent(activity, MlActivity::class.java)
				startActivity(intent)
			}
			backBtn.setOnClickListener { findNavController().navigateUp() }
			viewPager2.adapter = YogaViewPagerAdapter(childFragmentManager, lifecycle, yogaPose.yoga_instruction)
			TabLayoutMediator(tabLayout, viewPager2) { tab, _ ->
				tab.text = null
			}.attach()
		}
	}
}

class YogaViewPagerAdapter(
		fragmentManager: FragmentManager,
		lifecycle: Lifecycle,
		private val yogaInstruction: List<String>
) :
	FragmentStateAdapter(fragmentManager, lifecycle) {
	override fun getItemCount(): Int = yogaInstruction.size

	override fun createFragment(position: Int): Fragment {
		return YogaInstructionFragment(position + 1, yogaInstruction[position])
	}
}