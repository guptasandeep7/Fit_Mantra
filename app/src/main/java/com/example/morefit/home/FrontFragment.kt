package com.example.morefit.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentFrontBinding
import com.example.morefit.home.HomeFragment.Companion.muscleName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FrontFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFrontBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chest.setOnClickListener(this)
        binding.rightShoulder.setOnClickListener(this)
        binding.leftShoulder.setOnClickListener(this)
        binding.rightBiceps.setOnClickListener(this)
        binding.rightArm.setOnClickListener(this)
        binding.leftBiceps.setOnClickListener(this)
        binding.leftArm.setOnClickListener(this)
        binding.abs.setOnClickListener(this)
        binding.rightThigh.setOnClickListener(this)
        binding.leftThigh.setOnClickListener(this)
        binding.leftLeg.setOnClickListener(this)
        binding.rightLeg.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        muscleName = when (view?.id) {
            R.id.chest -> {
                view.setBackgroundResource(R.drawable.chest_red)
                "Chest"
            }
            R.id.right_shoulder -> {
                view.setBackgroundResource(R.drawable.right_shoulder)
                "Shoulders"
            }
            R.id.left_shoulder -> {
                view.setBackgroundResource(R.drawable.left_shoulder)
                "Shoulders"
            }
            R.id.right_biceps -> {
                view.setBackgroundResource(R.drawable.right_biceps)
                "Biceps"
            }
            R.id.right_arm -> {
                view.setBackgroundResource(R.drawable.right_arm)
                "Forearms"
            }
            R.id.left_biceps -> {
                view.setBackgroundResource(R.drawable.left_biceps)
                "Biceps"
            }
            R.id.left_arm -> {
                view.setBackgroundResource(R.drawable.left_arm)
                "Forearms"
            }
            R.id.abs -> {
                view.setBackgroundResource(R.drawable.abs)
                "Abdominals"
            }
            R.id.right_thigh -> {
                view.setBackgroundResource(R.drawable.right_thigh)
                "Quads"
            }
            R.id.left_thigh -> {
                view.setBackgroundResource(R.drawable.left_thigh)
                "Quads"
            }
            R.id.left_leg -> {
                view.setBackgroundResource(R.drawable.left_leg)
                "Calves"
            }
            R.id.right_leg -> {
                view.setBackgroundResource(R.drawable.right_leg)
                "Calves"
            }
            else -> {"Chest"}
        }
        lifecycleScope.launch {
            delay(100)
            findNavController().navigate(R.id.action_homefragment_to_exerciseFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFrontBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}