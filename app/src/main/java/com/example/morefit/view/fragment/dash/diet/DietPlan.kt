package com.example.morefit.view.fragment.dash.diet

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.morefit.R
import com.example.morefit.databinding.FragmentDietPlanBinding
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.Mealdata
import com.google.android.material.tabs.TabLayoutMediator
import java.text.SimpleDateFormat
import java.util.*

class DietPlan:Fragment(R.layout.fragment_diet_plan),View.OnClickListener {
    private lateinit var binding: FragmentDietPlanBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDietPlanBinding.bind(view)
        binding.monday.setOnClickListener(this)
        binding.tuesday.setOnClickListener(this)
        binding.wednesday.setOnClickListener(this)
        binding.thursday.setOnClickListener(this)
        binding.friday.setOnClickListener(this)
        binding.saturday.setOnClickListener(this)
        binding.sunday.setOnClickListener(this)
        binding.radioButton.setOnClickListener(this)
        binding.radioButton2.setOnClickListener(this)
        binding.radioButton3.setOnClickListener(this)

        if(Mealdata.week==null){
            val day:Calendar= Calendar.getInstance()
            binding.monday.text=SimpleDateFormat("EEEE", Locale.ENGLISH).format(day.time)
            binding.tuesday.visibility=View.GONE
            binding.wednesday.visibility=View.GONE
            binding.thursday.visibility=View.GONE
            binding.friday.visibility=View.GONE
            binding.saturday.visibility=View.GONE
            binding.sunday.visibility=View.GONE

            binding.breakfastTitle.text = Mealdata.meals?.get(0)?.title
            binding.breakfastQuantity.text =
                Mealdata.meals?.get(0)?.servings.toString() + " Servings"
            binding.breakfastCal.text = Mealdata.nutrients?.calories.toString()
            binding.lunchTitle.text = Mealdata.meals?.get(1)?.title
            binding.lunchQuantity.text =
                Mealdata.meals?.get(1)?.servings.toString() + " Servings"
            binding.lunchCal.text = Mealdata.nutrients?.calories.toString()
            binding.dinnerTitle.text = Mealdata.meals?.get(2)?.title
            binding.dinnerQuantity.text =
                Mealdata.meals?.get(2)?.servings.toString() + " Servings"
            binding.dinnerCal.text = Mealdata.nutrients?.calories.toString()
        }
        else {
            binding.breakfastTitle.text = Mealdata.week.monday?.meals?.get(0)?.title
            binding.breakfastQuantity.text =
                Mealdata.week.monday?.meals?.get(0)?.servings.toString() + " Servings"
            binding.breakfastCal.text = Mealdata.week.monday?.nutrients?.calories.toString()
            binding.lunchTitle.text = Mealdata.week.monday?.meals?.get(1)?.title
            binding.lunchQuantity.text =
                Mealdata.week.monday?.meals?.get(1)?.servings.toString() + " Servings"
            binding.lunchCal.text = Mealdata.week.monday?.nutrients?.calories.toString()
            binding.dinnerTitle.text = Mealdata.week.monday?.meals?.get(2)?.title
            binding.dinnerQuantity.text =
                Mealdata.week.monday?.meals?.get(2)?.servings.toString() + " Servings"
            binding.dinnerCal.text = Mealdata.week.monday?.nutrients?.calories.toString()
        }

    }

    private fun dietData(day:String){
        if (day=="monday"){
            binding.breakfastTitle.text=Mealdata.week.monday?.meals?.get(0)?.title
            binding.breakfastQuantity.text= Mealdata.week.monday?.meals?.get(0)?.servings.toString()+" Servings"
            binding.breakfastCal.text= Mealdata.week.monday?.nutrients?.calories.toString()
            binding.lunchTitle.text=Mealdata.week.monday?.meals?.get(1)?.title
            binding.lunchQuantity.text= Mealdata.week.monday?.meals?.get(1)?.servings.toString()+" Servings"
            binding.lunchCal.text= Mealdata.week.monday?.nutrients?.calories.toString()
            binding.dinnerTitle.text=Mealdata.week.monday?.meals?.get(2)?.title
            binding.dinnerQuantity.text= Mealdata.week.monday?.meals?.get(2)?.servings.toString()+" Servings"
            binding.dinnerCal.text= Mealdata.week.monday?.nutrients?.calories.toString()
        }
        else  if (day=="tuesday"){
            binding.breakfastTitle.text=Mealdata.week.tuesday?.meals?.get(0)?.title
            binding.breakfastQuantity.text= Mealdata.week.tuesday?.meals?.get(0)?.servings.toString()+" Servings"
            binding.breakfastCal.text= Mealdata.week.tuesday?.nutrients?.calories.toString()
            binding.lunchTitle.text=Mealdata.week.tuesday?.meals?.get(1)?.title
            binding.lunchQuantity.text= Mealdata.week.tuesday?.meals?.get(1)?.servings.toString()+" Servings"
            binding.lunchCal.text= Mealdata.week.tuesday?.nutrients?.calories.toString()
            binding.dinnerTitle.text=Mealdata.week.tuesday?.meals?.get(2)?.title
            binding.dinnerQuantity.text= Mealdata.week.tuesday?.meals?.get(2)?.servings.toString()+" Servings"
            binding.dinnerCal.text= Mealdata.week.tuesday?.nutrients?.calories.toString()
        }
        else  if (day=="wednesday"){
            binding.breakfastTitle.text=Mealdata.week.wednesday?.meals?.get(0)?.title
            binding.breakfastQuantity.text= Mealdata.week.wednesday?.meals?.get(0)?.servings.toString()+" Servings"
            binding.breakfastCal.text= Mealdata.week.wednesday?.nutrients?.calories.toString()
            binding.lunchTitle.text=Mealdata.week.wednesday?.meals?.get(1)?.title
            binding.lunchQuantity.text= Mealdata.week.wednesday?.meals?.get(1)?.servings.toString()+" Servings"
            binding.lunchCal.text= Mealdata.week.wednesday?.nutrients?.calories.toString()
            binding.dinnerTitle.text=Mealdata.week.wednesday?.meals?.get(2)?.title
            binding.dinnerQuantity.text= Mealdata.week.wednesday?.meals?.get(2)?.servings.toString()+" Servings"
            binding.dinnerCal.text= Mealdata.week.wednesday?.nutrients?.calories.toString()
        }
        else  if (day=="thursday"){
            binding.breakfastTitle.text=Mealdata.week.thursday?.meals?.get(0)?.title
            binding.breakfastQuantity.text= Mealdata.week.thursday?.meals?.get(0)?.servings.toString()+" Servings"
            binding.breakfastCal.text= Mealdata.week.thursday?.nutrients?.calories.toString()
            binding.lunchTitle.text=Mealdata.week.thursday?.meals?.get(1)?.title
            binding.lunchQuantity.text= Mealdata.week.thursday?.meals?.get(1)?.servings.toString()+" Servings"
            binding.lunchCal.text= Mealdata.week.thursday?.nutrients?.calories.toString()
            binding.dinnerTitle.text=Mealdata.week.thursday?.meals?.get(2)?.title
            binding.dinnerQuantity.text= Mealdata.week.thursday?.meals?.get(2)?.servings.toString()+" Servings"
            binding.dinnerCal.text= Mealdata.week.thursday?.nutrients?.calories.toString()
        }
        else  if (day=="friday"){
            binding.breakfastTitle.text=Mealdata.week.friday?.meals?.get(0)?.title
            binding.breakfastQuantity.text= Mealdata.week.friday?.meals?.get(0)?.servings.toString()+" Servings"
            binding.breakfastCal.text= Mealdata.week.friday?.nutrients?.calories.toString()
            binding.lunchTitle.text=Mealdata.week.friday?.meals?.get(1)?.title
            binding.lunchQuantity.text= Mealdata.week.friday?.meals?.get(1)?.servings.toString()+" Servings"
            binding.lunchCal.text= Mealdata.week.friday?.nutrients?.calories.toString()
            binding.dinnerTitle.text=Mealdata.week.friday?.meals?.get(2)?.title
            binding.dinnerQuantity.text= Mealdata.week.friday?.meals?.get(2)?.servings.toString()+" Servings"
            binding.dinnerCal.text= Mealdata.week.friday?.nutrients?.calories.toString()
        }
        else  if (day=="saturday"){
            binding.breakfastTitle.text=Mealdata.week.saturday?.meals?.get(0)?.title
            binding.breakfastQuantity.text= Mealdata.week.saturday?.meals?.get(0)?.servings.toString()+" Servings"
            binding.breakfastCal.text= Mealdata.week.saturday?.nutrients?.calories.toString()
            binding.lunchTitle.text=Mealdata.week.saturday?.meals?.get(1)?.title
            binding.lunchQuantity.text= Mealdata.week.saturday?.meals?.get(1)?.servings.toString()+" Servings"
            binding.lunchCal.text= Mealdata.week.saturday?.nutrients?.calories.toString()
            binding.dinnerTitle.text=Mealdata.week.saturday?.meals?.get(2)?.title
            binding.dinnerQuantity.text= Mealdata.week.saturday?.meals?.get(2)?.servings.toString()+" Servings"
            binding.dinnerCal.text= Mealdata.week.saturday?.nutrients?.calories.toString()
        }
        else  if (day=="sunday"){
            binding.breakfastTitle.text=Mealdata.week.sunday?.meals?.get(0)?.title
            binding.breakfastQuantity.text= Mealdata.week.sunday?.meals?.get(0)?.servings.toString()+" Servings"
            binding.breakfastCal.text= Mealdata.week.sunday?.nutrients?.calories.toString()
            binding.lunchTitle.text=Mealdata.week.sunday?.meals?.get(1)?.title
            binding.lunchQuantity.text= Mealdata.week.sunday?.meals?.get(1)?.servings.toString()+" Servings"
            binding.lunchCal.text= Mealdata.week.sunday?.nutrients?.calories.toString()
            binding.dinnerTitle.text=Mealdata.week.sunday?.meals?.get(2)?.title
            binding.dinnerQuantity.text= Mealdata.week.sunday?.meals?.get(2)?.servings.toString()+" Servings"
            binding.dinnerCal.text= Mealdata.week.sunday?.nutrients?.calories.toString()
        }

    }
    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id){
                R.id.monday->{
                    if (Mealdata.week!=null){
                        dietData("monday")
                    }

                }
                R.id.tuesday->dietData("tuesday")
                R.id.wednesday->dietData("wednesday")
                R.id.thursday->dietData("thursday")
                R.id.friday->dietData("friday")
                R.id.saturday->dietData("saturday")
                R.id.sunday->dietData("sunday")
                R.id.radioButton->{
                    if (binding.radioButton.isChecked) {
                        binding.radioButton2.isChecked = false
                        binding.radioButton3.isChecked = false
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                        binding.container3.setBackgroundResource(R.drawable.black_gradient)
                        binding.container2.setBackgroundResource(R.drawable.orange)
                    }
                    else{
                        binding.radioButton2.isChecked = false
                        binding.radioButton3.isChecked = false
                        binding.container2.setBackgroundResource(R.drawable.black_gradient)
                        binding.container3.setBackgroundResource(R.drawable.black_gradient)
                        binding.container1.setBackgroundResource(R.drawable.orange)
                    }
                }
                R.id.radioButton2->{
                    if (binding.radioButton2.isChecked) {
                        binding.radioButton3.isChecked = false
                        binding.radioButton.isChecked = true
                        binding.container2.setBackgroundResource(R.drawable.black_gradient)
                        binding.container3.setBackgroundResource(R.drawable.orange)
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                    }
                    else{
                        binding.radioButton.isChecked = true
                        binding.radioButton3.isChecked = false
                        binding.container2.setBackgroundResource(R.drawable.orange)
                        binding.container3.setBackgroundResource(R.drawable.black_gradient)
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                    }
                }
                R.id.radioButton3->{
                    if (binding.radioButton3.isChecked) {
                        binding.radioButton.isChecked = true
                        binding.radioButton2.isChecked = true
                        binding.container3.setBackgroundResource(R.drawable.black_gradient)
                        binding.container2.setBackgroundResource(R.drawable.black_gradient)
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                    }
                    else{
                        binding.radioButton.isChecked = true
                        binding.radioButton2.isChecked = true
                        binding.container3.setBackgroundResource(R.drawable.orange)
                        binding.container2.setBackgroundResource(R.drawable.black_gradient)
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                    }

                }
            }
        }
    }
}