package com.example.morefit.ui.fragment.dash.yoga

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.morefit.R
import com.example.morefit.databinding.FragmentYogaInstructionBinding

class YogaInstructionFragment(private val stepCount: Int, private var stepInstruction: String) :
	Fragment(R.layout.fragment_yoga_instruction) {
	private lateinit var binding: FragmentYogaInstructionBinding
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding = FragmentYogaInstructionBinding.bind(view)
		binding.stepCount.text = "Step $stepCount"
		
		val noOfLines = stepInstruction.length / 45
		if (noOfLines <= 7) {
			repeat(7 - noOfLines) {
				Log.e("COUNT", it.toString())
				stepInstruction += '\n'
			}
		}
		binding.stepInstruction.text = stepInstruction
	}
}