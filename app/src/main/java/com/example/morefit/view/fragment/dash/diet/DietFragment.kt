package com.example.morefit.view.fragment.dash.diet

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentDietBinding
import com.example.morefit.model.WeekPlan
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.Datastore.Companion.DIET_PLAN_KEY
import com.example.morefit.utils.Response
import com.example.morefit.view_models.GenerateMealPlanViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch

class DietFragment : Fragment(R.layout.fragment_diet), View.OnClickListener {
    private lateinit var binding: FragmentDietBinding
    private val bottomSheetDialog by lazy { BottomSheetDialog(requireContext()) }
    private val datastore by lazy { Datastore(requireContext()) }
    private val generateMealPlanViewModel by lazy {
        ViewModelProvider(this)[GenerateMealPlanViewModel::class.java]
    }
    companion object{
        lateinit var Mealdata:WeekPlan
    }
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
        val timeLayout=timeFrame.findViewById<TextInputLayout>(R.id.timeFrame_layout)
        val frameItems= listOf("Day","Week")

        caloryBtn.setOnClickListener {

            val cal=calory.text.toString()
            if (cal == ""){
                calLay.helperText="Minimum calories should be 500"
            }
            else if (cal.toInt() >= 500){
                lifecycleScope.launch {
                    datastore.saveUserDetails("CALORIES_KEY",calory.text.toString())
                }
                bottomSheetDialog.setContentView(timeFrame)
//                bottomSheetDialog.show()
                val adapter= context?.let { ArrayAdapter(it,R.layout.list_items,frameItems) }
                autoComplete.setAdapter(adapter)
                selectBtn.setOnClickListener{
                    if (autoComplete.text.toString()=="")
                    {
                        timeLayout.helperText="Choose a Time Frame"
                    }
                    else{
                        lifecycleScope.launch {
                            datastore.saveUserDetails("TIME_FRAME_KEY",autoComplete.text.toString().lowercase())
                            datastore.getUserDetails("TIME_FRAME_KEY")?.let { it1 ->
                                generateMealPlanViewModel.submitResult(
                                    datastore.getUserDetails("DIET_PLAN_KEY")!!,
                                    datastore.getUserDetails("CALORIES_KEY")!!,
                                    it1,"65bdc10ec0a435a72cb0a380dfa29c42e06eb228",datastore.apiKey())
                            }
                            Log.e("success", "showBottomSheet: "+"Succdesssss")
                            generateMealPlanViewModel._mealPlanResult.observe(viewLifecycleOwner) {
                                Log.e("success", "showBottomSheet: "+it.data)
                                if (it is Response.Success) {
                                    Log.e("success", "showBottomSheet: "+it.data)
                                    Mealdata= it.data!!
                                    bottomSheetDialog.dismiss()
                                    Toast.makeText(context, it.data.toString(), Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_dietFragment_to_dietPlan)
                                } else it.errorMessage?.let {
                                    Log.e("success", "showBottomSheet: "+it)
                                }
                            }
                        }
                    }

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
                R.id.GlutenFree->{
                    showBottomSheet()
                    lifecycleScope.launch{
                        datastore.saveUserDetails("DIET_PLAN_KEY","glutenfree")
                    }
                }
                R.id.ketogenic->{
                    showBottomSheet()
                    lifecycleScope.launch{
                        datastore.saveUserDetails("DIET_PLAN_KEY","ketogenic")
                    }
                }
                R.id.Vegan->{
                    showBottomSheet()
                    lifecycleScope.launch{
                        datastore.saveUserDetails("DIET_PLAN_KEY","vegan")
                    }
                }
                R.id.LactoVegetarian->{
                    showBottomSheet()
                    lifecycleScope.launch{
                        datastore.saveUserDetails("DIET_PLAN_KEY","Lacto-Vegetarian")
                    }
                }
                R.id.OvoVegetarian->{
                    showBottomSheet()
                    lifecycleScope.launch{
                        datastore.saveUserDetails("DIET_PLAN_KEY","Ovo-Vegetarian")
                    }
                }
                R.id.Whole30->{
                    showBottomSheet()
                    lifecycleScope.launch{
                        datastore.saveUserDetails("DIET_PLAN_KEY","Whole30")
                    }
                }

            }
        }
    }
}