package com.example.morefit.view.fragment.dash.diet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.morefit.R
import com.example.morefit.databinding.FragmentDietBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class DietFragment : Fragment(R.layout.fragment_diet), View.OnClickListener {
    private lateinit var binding: FragmentDietBinding
    private val bottomSheetDialog by lazy { BottomSheetDialog(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDietBinding.bind(view)
        binding.GlutenFree.setOnClickListener(this)
        binding.ketogenic.setOnClickListener(this)
        binding.Vegan.setOnClickListener(this)
        binding.LactoVegetarian.setOnClickListener(this)
        binding.OvoVegetarian.setOnClickListener(this)
        binding.Whole30.setOnClickListener(this)
    }
    private fun showBottomSheet(){
        val items =
            layoutInflater.inflate(R.layout.dialog_set_calories, null)
        val timeFrame=layoutInflater.inflate(R.layout.dialog_set_time_frame, null)
        bottomSheetDialog.setContentView(items)
        bottomSheetDialog.show()
        val calory=items.findViewById<TextInputEditText>(R.id.calory)
        val calLay=items.findViewById<TextInputLayout>(R.id.calory_layout)
        val autoComplete=timeFrame.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val caloryBtn=items.findViewById<MaterialButton>(R.id.enter_btn)
        val selectBtn=timeFrame.findViewById<MaterialButton>(R.id.select_btn)
        val frameItems= listOf("Day","Week")

        caloryBtn.setOnClickListener {
            val cal=calory.text.toString()
            if (cal == ""){
                calLay.helperText="Minimum calories should be 500"
            }
            else if (cal.toInt() >= 500){
                bottomSheetDialog.setContentView(timeFrame)
                bottomSheetDialog.show()
                val adapter= context?.let { ArrayAdapter(it,R.layout.list_items,frameItems) }
                autoComplete.setAdapter(adapter)
                selectBtn.setOnClickListener{
                    Toast.makeText(context,
                        autoComplete.text.toString().lowercase(), Toast.LENGTH_SHORT).show()
                }
            }
            else{
                calLay.helperText="Minimum calories should be 500"
            }
        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.GlutenFree->showBottomSheet()
                R.id.ketogenic->showBottomSheet()
                R.id.Vegan->showBottomSheet()
                R.id.LactoVegetarian->showBottomSheet()
                R.id.OvoVegetarian->showBottomSheet()
                R.id.Whole30->showBottomSheet()

            }
        }
    }
}