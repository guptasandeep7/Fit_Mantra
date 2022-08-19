package com.example.morefit.ui.fragment.dash.gym.home

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentBackBinding
import com.example.morefit.ui.fragment.dash.gym.GymFragment.Companion.muscleName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BackFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentBackBinding? = null
    private val binding get() = _binding!!
    private var mLastClickTime: Long = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backTop.setOnClickListener(this)
        binding.backRightBiceps.setOnClickListener(this)
        binding.backLeftBiceps.setOnClickListener(this)
        binding.backLeftShoulder.setOnClickListener(this)
        binding.backRightShoulder.setOnClickListener(this)
        binding.backRightArm.setOnClickListener(this)
        binding.backLeftArm.setOnClickListener(this)
        binding.backRightLats.setOnClickListener(this)
        binding.backLeftLats.setOnClickListener(this)
        binding.backRightThigh.setOnClickListener(this)
        binding.backLeftThigh.setOnClickListener(this)
        binding.backRightLeg.setOnClickListener(this)
        binding.backLeftLeg.setOnClickListener(this)
        binding.backLowerBack.setOnClickListener(this)
        binding.backGlutes.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return
        } else {
            muscleName = when (view?.id) {
                R.id.back_top -> {
                    view.setBackgroundResource(R.drawable.back_top)
                    "Traps"
                }
                R.id.back_right_biceps -> {
                    view.setBackgroundResource(R.drawable.back_right_biceps)
                    "Triceps"
                }
                R.id.back_left_biceps -> {
                    view.setBackgroundResource(R.drawable.back_left_biceps)
                    "Triceps"
                }
                R.id.back_left_shoulder -> {
                    view.setBackgroundResource(R.drawable.back_left_shoulder)
                    "Shoulders"
                }
                R.id.back_right_shoulder -> {
                    view.setBackgroundResource(R.drawable.back_right_shoulder)
                    "Shoulders"
                }
                R.id.back_right_arm -> {
                    view.setBackgroundResource(R.drawable.back_right_arm)
                    "Forearms"
                }
                R.id.back_left_arm -> {
                    view.setBackgroundResource(R.drawable.back_left_arm)
                    "Forearms"
                }
                R.id.back_right_lats -> {
                    view.setBackgroundResource(R.drawable.back_right_lats)
                    "Lats"
                }
                R.id.back_left_lats -> {
                    view.setBackgroundResource(R.drawable.back_left_lats)
                    "Lats"
                }
                R.id.back_right_thigh -> {
                    view.setBackgroundResource(R.drawable.back_right_thigh)
                    "Hamstrings"
                }
                R.id.back_left_thigh -> {
                    view.setBackgroundResource(R.drawable.back_left_thigh)
                    "Hamstrings"
                }
                R.id.back_right_leg -> {
                    view.setBackgroundResource(R.drawable.back_right_leg)
                    "Hamstrings"
                }
                R.id.back_left_leg -> {
                    view.setBackgroundResource(R.drawable.back_left_leg)
                    "Hamstrings"
                }
                R.id.back_lower_back -> {
                    view.setBackgroundResource(R.drawable.back_lower_back)
                    "Lowerback"
                }
                R.id.back_glutes -> {
                    view.setBackgroundResource(R.drawable.back_glutes)
                    "Glutes"
                }
                else -> {"Traps"}
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
    ): View? {
        _binding = FragmentBackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}