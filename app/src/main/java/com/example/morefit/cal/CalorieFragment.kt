package com.example.morefit.cal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentCalorieBinding
import kotlin.math.floor

class CalorieFragment : Fragment() {

    private var _binding: FragmentCalorieBinding? = null
    private val binding get() = _binding!!
    lateinit var categories:List<String>
    private var selectedTarget:Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories = resources.getStringArray(R.array.category_array).toMutableList()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        binding.category.adapter = adapter

        binding.category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectedTarget = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        binding.calculateBtn.setOnClickListener {
            helper()
            if (isValid()) {
                val weight = binding.weight.text.toString().toDouble()
                val height = binding.height.text.toString().toDouble()
                val age = binding.age.text.toString().toDouble()
                when(selectedTarget){
                    0 -> calBmr(weight, height, age,1.2)
                    1 -> calBmr(weight, height, age,1.375)
                    2 -> calBmr(weight, height, age,1.55)
                    3 -> calBmr(weight, height, age,1.725)
                    4 -> calBmr(weight, height, age,1.9)
                }

            }
        }

        binding.backBtn.setOnClickListener { findNavController().navigateUp() }

    }

    private fun calBmr(weight: Double, height: Double, age: Double, cal: Double) {
        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.male -> {
                val bmr = ((10 * weight) + (6.25 * height) - (5 * age) + 5)*cal
                binding.bmi.text = "Your would have consume \n${floor(bmr)}"
            }
            R.id.female -> {
                val bmr = ((10 * weight) + (6.25 * height) - (5 * age) - 16)*cal
                binding.bmi.text = "Your would have consume \n${floor(bmr)}"
            }
        }
    }

    private fun helper() {
        binding.weightLayout.helperText = ""
        binding.heightLayout.helperText = ""
        binding.ageLayout.helperText = ""
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
            binding.age.text.isNullOrBlank() -> {
                binding.heightLayout.helperText = "Please enter age"
                false
            }
            else -> true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalorieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}