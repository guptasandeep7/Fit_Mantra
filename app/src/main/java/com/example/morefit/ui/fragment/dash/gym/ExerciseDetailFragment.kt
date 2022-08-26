package com.example.morefit.ui.fragment.dash.gym

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import coil.load
import coil.size.Scale
import com.example.morefit.R
import com.example.morefit.adapter.imageFromUrl
import com.example.morefit.databinding.FragmentYogaPoseDetailsBinding
import com.example.morefit.model.TextTutorial
import com.example.morefit.ui.fragment.dash.yoga.YogaInstructionFragment
import com.example.morefit.ui.fragment.dash.yoga.YogaPoseDetailsFragmentArgs
import com.example.morefit.ui.fragment.dash.yoga.YogaViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ExerciseDetailFragment: Fragment(R.layout.fragment_yoga_pose_details) {
    private lateinit var binding : FragmentYogaPoseDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentYogaPoseDetailsBinding.bind(view).apply {
            val data = ExerciseDetailFragmentArgs.fromBundle(requireArguments()).data
            englishName.text = data.title
            yogaDescription.visibility = View.GONE
            sanskritName.text = data.difficulty
            poseImg.imageFromUrl(data.video_tutorials[0])
            viewPager2.adapter = ExerciseDetailPagerAdapter(childFragmentManager, lifecycle, data.text_tutorials)
            TabLayoutMediator(tabLayout, viewPager2) { tab, _ ->
                tab.text = null
            }.attach()
            backBtn.setOnClickListener { findNavController().navigateUp() }
            PerformAsana.visibility = View.GONE
        }
    }
}

class ExerciseDetailPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val tutorial: List<TextTutorial>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = tutorial.size

    override fun createFragment(position: Int): Fragment {
        return YogaInstructionFragment(position + 1, tutorial[position].text)
    }
}