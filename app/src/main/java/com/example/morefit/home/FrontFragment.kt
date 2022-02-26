package com.example.morefit.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentFrontBinding
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

    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.chest -> view.setBackgroundResource(R.drawable.chest_red)
            R.id.right_shoulder -> view.setBackgroundResource(R.drawable.right_shoulder)
            R.id.left_shoulder -> view.setBackgroundResource(R.drawable.left_shoulder)
            R.id.right_biceps -> view.setBackgroundResource(R.drawable.right_biceps)
            R.id.right_arm -> view.setBackgroundResource(R.drawable.right_arm)
            R.id.left_biceps -> view.setBackgroundResource(R.drawable.left_biceps)
            R.id.left_arm -> view.setBackgroundResource(R.drawable.left_arm)
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