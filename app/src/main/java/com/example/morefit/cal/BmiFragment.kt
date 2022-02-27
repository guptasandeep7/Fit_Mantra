package com.example.morefit.cal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morefit.databinding.FragmentBmiBinding
import kotlin.math.floor
import kotlin.math.pow

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateBtn.setOnClickListener {
            helper()
            if (isValid()) {
                val weight = binding.weight.text.toString().toDouble()
                val height = binding.height.text.toString().toDouble()/100
                val bmi = weight / height.pow(2.0)
                binding.bmi.text = "Your BMI is \n${floor(bmi)}"
            }
        }

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
    }

    private fun helper() {
        binding.weightLayout.helperText = ""
        binding.heightLayout.helperText = ""
    }

    private fun isValid(): Boolean {
        return when {
            binding.weight.text.isNullOrBlank() -> {
                binding.weightLayout.helperText = "Please enter weight"
                false
            }
            binding.height.text.isNullOrBlank() -> {
                binding.heightLayout.helperText = "Please enter height"
                false
            }
            else -> true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}