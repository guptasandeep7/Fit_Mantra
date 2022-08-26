package com.example.morefit.ui.fragment.dash.yoga

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
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
import com.example.morefit.utils.hideBottomNavigationView
import com.example.morefit.utils.showBottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.transition.MaterialSharedAxis
import java.util.*
import org.tensorflow.lite.examples.poseestimation.ml.PoseClassifier

class YogaPoseDetailsFragment : Fragment(R.layout.fragment_yoga_pose_details) {
	private lateinit var binding: FragmentYogaPoseDetailsBinding
	lateinit var textToSpeech: TextToSpeech
	override fun onStart() {
		super.onStart()
		activity?.hideBottomNavigationView()
	}
	
	override fun onStop() {
		super.onStop()
		activity?.showBottomNavigationView()
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
		// create an object textToSpeech and adding features into it
		// create an object textToSpeech and adding features into it
		textToSpeech = TextToSpeech(requireContext()) { i ->
			// if No error is found then only it will run
			if (i != TextToSpeech.ERROR) {
				// To Choose language of speech
				textToSpeech.setLanguage(Locale.UK)
			}
		}

		returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding = FragmentYogaPoseDetailsBinding.bind(view).apply {
			val yogaPose = YogaPoseDetailsFragmentArgs.fromBundle(requireArguments()).yogaPose
			englishName.text = "${yogaPose.english_name} Pose"

			sanskritName.text = "Sanskrit \"${yogaPose.sanskrit_name}\""
			yogaDescription.text = yogaPose.yoga_description
			textToSpeech.speak(yogaPose.english_name,TextToSpeech.QUEUE_FLUSH,null);
			textToSpeech.speak(yogaPose.sanskrit_name,TextToSpeech.QUEUE_FLUSH,null)
			poseImg.load(yogaPose.image_url) {
				crossfade(true)
				crossfade(300)
				placeholder(R.drawable.ic_yoga)
				error(R.drawable.ic_yoga)
				scale(Scale.FILL)
			}
			backBtn.setOnClickListener { findNavController().navigateUp() }

			viewPager2.adapter = YogaViewPagerAdapter(childFragmentManager, lifecycle, yogaPose.yoga_instruction)
			TabLayoutMediator(tabLayout, viewPager2) { tab, _ ->
				tab.text = null
			}.attach()

			PerformAsana.setOnClickListener {
				PoseClassifier.MODEL_FILENAME=yogaPose.file_name
				MlActivity.correct_label=yogaPose.correct_label
				MlActivity.name=yogaPose.english_name
				PoseClassifier.labels=yogaPose.labels
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