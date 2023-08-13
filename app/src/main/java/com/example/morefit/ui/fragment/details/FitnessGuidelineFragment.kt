package com.example.morefit.ui.fragment.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.morefit.R
import com.example.morefit.databinding.FragmentFitnessGuidelinesBinding
import com.example.morefit.ui.activity.MainActivity

class FitnessGuidelineFragment: Fragment(R.layout.fragment_fitness_guidelines), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFitnessGuidelinesBinding? = null
    private val binding get() = _binding!!

    private val guidelinesList = listOf(
        "Leg Injury",
        "Hand Injury",
        "Shoulder Injury",
        "Neck Injury",
        "Pregnant Women"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFitnessGuidelinesBinding.bind(view)
        binding.nextBtn.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        binding.spinner.onItemSelectedListener = this

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, guidelinesList)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.item_dropdown)

        // Apply the adapter to the spinner
        binding.spinner.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}