package com.example.morefit.ui.fragment.dash.diet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentDietBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.transition.platform.MaterialFadeThrough

class DietFragment : Fragment(R.layout.fragment_diet), View.OnClickListener {
    private lateinit var binding: FragmentDietBinding
    private val bottomSheetDialog by lazy { BottomSheetDialog(requireContext()) }

    companion object{
        lateinit var qBreak:String
        lateinit var qLunch:String
        lateinit var qDinner:String
        lateinit var health:String
        lateinit var calorie:String
        lateinit var breakRoti:String
        lateinit var breakRice:String
        lateinit var lunchRoti:String
        lateinit var lunchRice:String
        lateinit var dinnerRoti:String
        lateinit var dinnerRice:String
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDietBinding.bind(view)
        binding.GlutenFree.setOnClickListener(this)
        binding.ketogenic.setOnClickListener(this)
        binding.Vegan.setOnClickListener(this)
        binding.Vegetarian.setOnClickListener(this)
        binding.PeanutFree.setOnClickListener(this)
        binding.FishFree.setOnClickListener(this)
        binding.SugarFree.setOnClickListener(this)
        binding.DairyFree.setOnClickListener(this)
        binding.EggFree.setOnClickListener(this)
        binding.ImmunoSupportive.setOnClickListener(this)
        binding.KidneyFriendly.setOnClickListener(this)
        binding.LowSugar.setOnClickListener(this)
        binding.MustardFree.setOnClickListener(this)
        binding.NoOilAdded.setOnClickListener(this)
        binding.LowFatAbs.setOnClickListener(this)
        binding.ShellFishFree.setOnClickListener(this)
    }

    private fun showBottomSheet(){
        val items =
            layoutInflater.inflate(R.layout.dialog_set_calories, null)
        bottomSheetDialog.setContentView(items)
        bottomSheetDialog.show()
        val calory=items.findViewById<TextInputEditText>(R.id.calory)
        val calLay=items.findViewById<TextInputLayout>(R.id.calory_layout)
        val caloryBtn=items.findViewById<MaterialButton>(R.id.enter_btn)
        val ingredientBreak=items.findViewById<TextInputEditText>(R.id.ingredient_break)
        val ingredientLayoutBreak=items.findViewById<TextInputLayout>(R.id.ingredient_layout_break)
        val ingredientLunch=items.findViewById<TextInputEditText>(R.id.ingredient_lunch)
        val ingredientLayoutLunch=items.findViewById<TextInputLayout>(R.id.ingredient_layout_lunch)
        val ingredientDinner=items.findViewById<TextInputEditText>(R.id.ingredient_dinner)
        val ingredientLayoutDinner=items.findViewById<TextInputLayout>(R.id.ingredient_layout_dinner)
        val plus1=items.findViewById<Button>(R.id.plus)
        val plus2=items.findViewById<Button>(R.id.plus2)
        val plus3=items.findViewById<Button>(R.id.plus3)
        val minus1=items.findViewById<Button>(R.id.minus)
        val minus2=items.findViewById<Button>(R.id.minus2)
        val minus3=items.findViewById<Button>(R.id.minus3)
        val counter1=items.findViewById<TextView>(R.id.textView8)
        val counter2=items.findViewById<TextView>(R.id.textView11)
        val counter3=items.findViewById<TextView>(R.id.textView13)
        val check1=items.findViewById<CheckBox>(R.id.radioBtn1)
        val check2=items.findViewById<CheckBox>(R.id.radioBtn2)
        val check3=items.findViewById<CheckBox>(R.id.radioBtn3)
        var num1=0
        var num2=0
        var num3=0
        plus1.setOnClickListener {
            ++num1
            counter1.text=num1.toString()
        }
        plus2.setOnClickListener {
            ++num2
            counter2.text=num2.toString()
        }
        plus3.setOnClickListener {
            ++num3
            counter3.text=num3.toString()
        }
        minus1.setOnClickListener {
            if (num1==0){
                num1=0
            }else{
                --num1
            }
            counter1.text=num1.toString()
        }
        minus2.setOnClickListener {
            if (num2==0){
                num2=0
            }else{
                --num2
            }
            counter2.text=num2.toString()
        }
        minus3.setOnClickListener {
            if (num3==0){
                num3=0
            }else{
                --num3
            }
            counter3.text=num3.toString()
        }
        caloryBtn.setOnClickListener {

            calorie=calory.text.toString()
            if(calorie == ""){
                calLay.helperText="Minimum calories should be 1000"
            }
            else if (calorie.toInt() < 1000){
                calLay.helperText="Minimum calories should be 1000"
            }
            else if(ingredientBreak.text.toString() == ""){
                ingredientLayoutBreak.helperText="Enter some ingredient for breakfast"
            }
            else if(ingredientLunch.text.toString() == ""){
                ingredientLayoutLunch.helperText="Enter some ingredient for lunch"
            }
            else if(ingredientDinner.text.toString() == ""){
                ingredientLayoutDinner.helperText="Enter some ingredient for dinner"
            }
            else{
                var cal=(num1+num2+num3)*104
                if (check1.isChecked){
                    cal += 136
                    breakRice="1"
                }
                else breakRice="0"
                if (check2.isChecked){
                    cal += 136
                    lunchRice="1"
                }else lunchRice="0"
                if (check3.isChecked){
                    cal += 13
                    dinnerRice="1"
                }else dinnerRice="0"
                breakRoti=num1.toString()
                lunchRoti=num2.toString()
                dinnerRoti=num3.toString()
                qBreak=ingredientBreak.text.toString()
                qLunch=ingredientLunch.text.toString()
                qDinner=ingredientDinner.text.toString()
                calorie=(calory.text.toString().toInt()-cal).toString()
                bottomSheetDialog.dismiss()
                findNavController().navigate(R.id.action_dietFragment_to_dietPlan)
            }

        }
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.GlutenFree->{
                    health="gluten-free"
                    showBottomSheet()

                }
                R.id.ketogenic->{
                    health="keto-friendly"
                    showBottomSheet()

                }
                R.id.Vegan->{
                    health="vegan"
                    showBottomSheet()

                }
                R.id.Vegetarian->{
                    health="vegetarian"
                    showBottomSheet()

                }
                R.id.PeanutFree->{
                    health="peanut-free"
                    showBottomSheet()

                }
                R.id.FishFree->{
                    health="fish-free"
                    showBottomSheet()

                }
                R.id.NoOilAdded->{
                    health="wheat-free"
                    showBottomSheet()

                }
                R.id.SugarFree->{
                    health="sugar-free"
                    showBottomSheet()

                }
                R.id.DairyFree->{
                    health="dairy-free"
                    showBottomSheet()

                }
                R.id.EggFree->{
                    health="egg-free"
                    showBottomSheet()

                }
                R.id.ImmunoSupportive->{
                    health="immuno-supportive"
                    showBottomSheet()

                }
                R.id.KidneyFriendly->{
                    health="kidney-friendly"
                    showBottomSheet()

                }
                R.id.LowSugar->{
                    health="low-sugar"
                    showBottomSheet()

                }
                R.id.MustardFree->{
                    health="mustard-free"
                    showBottomSheet()

                }
                R.id.ShellFishFree->{
                    health="shellfish-free"
                    showBottomSheet()

                }
                R.id.LowFatAbs->{
                    health="low-fat-abs"
                    showBottomSheet()

                }

            }
        }
    }
}