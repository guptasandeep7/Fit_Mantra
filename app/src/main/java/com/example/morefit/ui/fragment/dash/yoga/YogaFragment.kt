package com.example.morefit.ui.fragment.dash.yoga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.adapter.YogaPoseInterface
import com.example.morefit.adapter.YogaPoseRecyclerAdapter
import com.example.morefit.databinding.FragmentYogaBinding
import com.example.morefit.model.Pose
import com.example.morefit.model.YogaPoses
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.google.gson.Gson

class YogaFragment : Fragment(R.layout.fragment_yoga), YogaPoseInterface {
    private lateinit var binding: FragmentYogaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentYogaBinding.bind(view).apply {
            val file = requireContext().assets.open("pregnant_yoga_data.json").bufferedReader()
                .use { it.readText() }
            val data = Gson().fromJson(file, YogaPoses::class.java)
            yogaRecyclerView.adapter = YogaPoseRecyclerAdapter(this@YogaFragment, data.poses)

            icBack.setOnClickListener { findNavController().navigateUp() }
        }
    }

    override fun onPoseClickListener(yogaPoseDetail: Pose) {
        YogaFragmentDirections.actionYogaFragmentToYogaPoseDetailsFragment(yogaPoseDetail).also {
            findNavController().navigate(it)
        }
    }
}