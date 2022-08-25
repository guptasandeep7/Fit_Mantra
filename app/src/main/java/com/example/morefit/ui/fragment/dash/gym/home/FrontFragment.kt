package com.example.morefit.ui.fragment.dash.gym.home

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentFrontBinding
import com.example.morefit.ui.fragment.dash.gym.GymFragment.Companion.muscleName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FrontFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFrontBinding? = null
    private val binding get() = _binding!!
    private var mLastClickTime: Long = 0

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
    }

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return
        } else {
            mLastClickTime = SystemClock.elapsedRealtime()
            muscleName = when (view?.id) {
                R.id.chest -> {
                    binding.imageView.setBackgroundResource(R.drawable.chest)
                    "Chest"
                }
                R.id.right_shoulder -> {
                    binding.imageView.setBackgroundResource(R.drawable.right_shoulder)
                    "Shoulders"
                }
                R.id.left_shoulder -> {
                    binding.imageView.setBackgroundResource(R.drawable.left_shoulder)
                    "Shoulders"
                }
                R.id.right_biceps -> {
                    binding.imageView.setBackgroundResource(R.drawable.right_bicep)
                    "Biceps"
                }
                R.id.right_arm -> {
                    binding.imageView.setBackgroundResource(R.drawable.forearm_right)
                    "Forearms"
                }
                R.id.left_biceps -> {
                    binding.imageView.setBackgroundResource(R.drawable.left_bicep)
                    "Biceps"
                }
                R.id.left_arm -> {
                    binding.imageView.setBackgroundResource(R.drawable.forearm_left)
                    "Forearms"
                }
                R.id.abs -> {
                    binding.imageView.setBackgroundResource(R.drawable.abs)
                    "Abdominals"
                }
                R.id.right_thigh -> {
                    binding.imageView.setBackgroundResource(R.drawable.right_quad)
                    "Hamstrings"
                }
                R.id.left_thigh -> {
                    binding.imageView.setBackgroundResource(R.drawable.left_quad)
                    "Hamstrings"
                }
                else -> {
                    "Chest"
                }
            }
            lifecycleScope.launch {
                delay(100)
                findNavController().navigate(R.id.action_gymFragment_to_exerciseFragment2)
            }
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