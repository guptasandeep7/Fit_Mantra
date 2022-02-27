package com.example.morefit.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentBackBinding
import com.example.morefit.databinding.FragmentFrontBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BackFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentBackBinding? = null
    private val binding get() = _binding!!

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
        when(view?.id){
            R.id.back_top -> view.setBackgroundResource(R.drawable.back_top)
            R.id.back_right_biceps -> view.setBackgroundResource(R.drawable.back_right_biceps)
            R.id.back_left_biceps -> view.setBackgroundResource(R.drawable.back_left_biceps)
            R.id.back_left_shoulder -> view.setBackgroundResource(R.drawable.back_left_shoulder)
            R.id.back_right_shoulder -> view.setBackgroundResource(R.drawable.back_right_shoulder)
            R.id.back_right_arm -> view.setBackgroundResource(R.drawable.back_right_arm)
            R.id.back_left_arm -> view.setBackgroundResource(R.drawable.back_left_arm)
            R.id.back_right_lats -> view.setBackgroundResource(R.drawable.back_right_lats)
            R.id.back_left_lats -> view.setBackgroundResource(R.drawable.back_left_lats)
            R.id.back_right_thigh -> view.setBackgroundResource(R.drawable.back_right_thigh)
            R.id.back_left_thigh -> view.setBackgroundResource(R.drawable.back_left_thigh)
            R.id.back_right_leg -> view.setBackgroundResource(R.drawable.back_right_leg)
            R.id.back_left_leg -> view.setBackgroundResource(R.drawable.back_left_leg)
            R.id.back_lower_back -> view.setBackgroundResource(R.drawable.back_lower_back)
            R.id.back_glutes -> view.setBackgroundResource(R.drawable.back_glutes)
        }
        lifecycleScope.launch {
            delay(100)
            findNavController().navigate(R.id.action_homefragment_to_exerciseFragment)
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