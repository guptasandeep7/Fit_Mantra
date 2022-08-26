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
import com.example.morefit.databinding.FragmentMacroBinding
import com.google.android.material.transition.MaterialSharedAxis

class MacroFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentMacroBinding? = null
    private val binding get() = _binding!!
    private var choice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.first.setOnClickListener(this)
        binding.second.setOnClickListener(this)
        binding.third.setOnClickListener(this)
        binding.fourth.setOnClickListener(this)

        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMacroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.first -> check(1)
            R.id.second -> check(2)
            R.id.third -> check(3)
            R.id.fourth -> check(4)
        }
    }

    private fun check(choice: Int) {
        if (binding.calorie.text.isNullOrBlank())
            binding.calorieLayout.helperText = "Enter Calorie"
        else {
            binding.calorieLayout.helperText = ""
            this.choice = choice
            calculateMacro()
        }
    }

    private fun calculateMacro() {
        val dialog = Dialog(requireContext())
        val layout = layoutInflater.inflate(R.layout.dialog_macro, null)
        dialog.setContentView(layout)
        dialog.setCancelable(true)
        val carb = layout.findViewById<TextView>(R.id.carb)
        val protein = layout.findViewById<TextView>(R.id.protein)
        val fat = layout.findViewById<TextView>(R.id.fat)
        val calorie = binding.calorie.text.toString().toInt()

        when (choice) {
            1 -> {
                carb.text = ((calorie * 0.60) / 4).toInt().toString() + "g"
                protein.text = ((calorie * 0.25) / 4).toInt().toString() + "g"
                fat.text = ((calorie * 0.15) / 4).toInt().toString() + "g"
            }
            2 -> {
                carb.text = ((calorie * 0.50) / 4).toInt().toString() + "g"
                protein.text = ((calorie * 0.30) / 4).toInt().toString() + "g"
                fat.text = ((calorie * 0.20) / 4).toInt().toString() + "g"
            }
            3 -> {
                carb.text = ((calorie * 0.40) / 4).toInt().toString() + "g"
                protein.text = ((calorie * 0.30) / 4).toInt().toString() + "g"
                fat.text = ((calorie * 0.30) / 4).toInt().toString() + "g"
            }
            4 -> {
                carb.text = ((calorie * 0.25) / 4).toInt().toString() + "g"
                protein.text = ((calorie * 0.45) / 4).toInt().toString() + "g"
                fat.text = ((calorie * 0.30) / 4).toInt().toString() + "g"
            }
        }

        dialog.show()
    }
}