package com.example.morefit.ui.fragment.dash.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.CalOptionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CalculateBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: CalOptionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CalOptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bmiCalBtn.setOnClickListener {
            findNavController().navigate(R.id.action_calculateBottomSheetFragment_to_bmiFragment2)
        }

        binding.calorieMeterBtn.setOnClickListener {
            findNavController().navigate(R.id.action_calculateBottomSheetFragment_to_calorieFragment2)
        }

        binding.macroCalBtn.setOnClickListener {
            findNavController().navigate(R.id.action_calculateBottomSheetFragment_to_macroFragment2)
        }
    }
}