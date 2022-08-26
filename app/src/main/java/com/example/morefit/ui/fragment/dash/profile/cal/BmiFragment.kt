package com.example.morefit.ui.fragment.dash.profile.cal

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentBmiBinding
import com.google.android.material.transition.MaterialSharedAxis
import kotlin.math.pow

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculateBtn.setOnClickListener {
            helper()
            if (isValid()) {
                val weight = binding.weight.text.toString().toDouble()
                val height = binding.height.text.toString().toDouble()/100
                val bmi = weight / height.pow(2.0)
                bmiDialog(bmi.toInt())
            }
        }

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }
    }

    private fun bmiDialog(cal:Int){
        val dialog = Dialog(requireContext())
        val layout = layoutInflater.inflate(R.layout.dialog_bmi, null)
        dialog.setContentView(layout)
        dialog.setCancelable(true)
        val calorie = layout.findViewById<TextView>(R.id.bmi)
        calorie.text = cal.toString()
        dialog.show()
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