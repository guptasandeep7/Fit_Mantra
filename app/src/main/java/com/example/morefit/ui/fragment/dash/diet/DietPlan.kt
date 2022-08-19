package com.example.morefit.view.fragment.dash.diet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.morefit.R
import com.example.morefit.databinding.FragmentDietPlanBinding
import com.example.morefit.model.WeekMeal
import com.example.morefit.utils.Response
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.breakRice
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.breakRoti
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.calorie
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.dinnerRice
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.dinnerRoti
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.health
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.lunchRice
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.lunchRoti
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.qBreak
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.qDinner
import com.example.morefit.view.fragment.dash.diet.DietFragment.Companion.qLunch
import com.example.morefit.view_models.GenerateMealPlanViewModel
import kotlin.math.roundToInt


class DietPlan : Fragment(R.layout.fragment_diet_plan), View.OnClickListener {
    private lateinit var binding: FragmentDietPlanBinding
    lateinit var Mealdata:MutableList<WeekMeal>
    lateinit var Mealdatalunch:MutableList<WeekMeal>
    lateinit var MealdataDinner:MutableList<WeekMeal>
    var breakfast=""
    var lunch=""
    var dinner= ""
    private val generateMealPlanViewModel by lazy { ViewModelProvider(this)[GenerateMealPlanViewModel::class.java]    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDietPlanBinding.bind(view)
        binding.radioButton.setOnClickListener(this)
        binding.radioButton2.setOnClickListener(this)
        binding.radioButton3.setOnClickListener(this)
        binding.breakfastSite.setOnClickListener(this)
        binding.lunchSite.setOnClickListener(this)
        binding.dinnerSite.setOnClickListener(this)
        Toast.makeText(context, calorie, Toast.LENGTH_SHORT).show()
        Mealdata= mutableListOf()
        Mealdatalunch= mutableListOf()
        MealdataDinner= mutableListOf()
        val calorieBreak:String = (calorie.toInt()*0.25).toString()+"-"+(calorie.toInt()*0.3).toString()
        val calorieLunch:String = (calorie.toInt()*0.35).toString()+"-"+(calorie.toInt()*0.4).toString()
        val calorieDinner:String = (calorie.toInt()*0.25).toString()+"-"+(calorie.toInt()*0.3).toString()

        binding.progressBar.visibility=View.VISIBLE
        binding.breakfast.visibility=View.GONE
        binding.lunch.visibility=View.GONE
        binding.dinner.visibility=View.GONE
        var count =0
        generateMealPlanViewModel.submitResult("public",qBreak,"16a70afb","96079c81218f8debd87360747fd41368",health,"Indian","Breakfast",calorieBreak)
        generateMealPlanViewModel._mealPlanResult.observe(viewLifecycleOwner) {
            if (it is Response.Success) {
                    Mealdata.add(it.data!!)
                    count++
                    call(count)
            } else it.errorMessage?.let {
                Log.e("mssg", "showBottomSheet: error" )
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        generateMealPlanViewModel.submitResult("public",qLunch,"16a70afb","96079c81218f8debd87360747fd41368",health,"Indian","Lunch",calorieLunch)
        generateMealPlanViewModel._mealPlanResult.observe(viewLifecycleOwner) {
            if (it is Response.Success) {
                    Mealdatalunch.add(it.data!!)
                    count++
                    call(count)
            } else it.errorMessage?.let {
                Log.e("mssg", "showBottomSheet: error" )
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        generateMealPlanViewModel.submitResult("public",qDinner,"16a70afb","96079c81218f8debd87360747fd41368",health,"Indian","Dinner",calorieDinner)
        generateMealPlanViewModel._mealPlanResult.observe(viewLifecycleOwner) {
            if (it is Response.Success) {
                    MealdataDinner.add(it.data!!)
                    count++
                    call(count)
            } else it.errorMessage?.let {
                Log.e("mssg", "showBottomSheet: error" )
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }



    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.radioButton -> {
                    if (binding.radioButton.isChecked) {
                        binding.radioButton2.isChecked = false
                        binding.radioButton3.isChecked = false
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                        binding.container3.setBackgroundResource(R.drawable.orange)
                        binding.container2.setBackgroundResource(R.drawable.orange)
                    }
                    else{
                        binding.radioButton2.isChecked = false
                        binding.radioButton3.isChecked = false
                        binding.container2.setBackgroundResource(R.drawable.orange)
                        binding.container3.setBackgroundResource(R.drawable.orange)
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
                        binding.container3.setBackgroundResource(R.drawable.orange)
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

    private fun golink(sourceUrl: String) {

        val uri:Uri=Uri.parse(sourceUrl)
        startActivity(Intent(Intent.ACTION_VIEW,uri))
    }
    private fun call(count:Int){
        var breakfst=1
        var din=1
        var lun=1
        if(count == 3){
            binding.progressBar.visibility=View.GONE
            binding.breakfast.visibility=View.VISIBLE
            binding.lunch.visibility=View.VISIBLE
            binding.dinner.visibility=View.VISIBLE
            if (Mealdata[0].count == 0){
                breakfst=0
                breakfast="No results for breakfast"
                binding.breakfast.visibility=View.GONE
                binding.errorTextBreak.visibility=View.VISIBLE
                binding.errorTextBreak.text=breakfast+"\n"+lunch+"\n"+dinner
                ( binding.lunch.layoutParams as ConstraintLayout.LayoutParams).apply {
                    marginStart = 24
                    topMargin = 50
                    marginEnd = 24
                    bottomMargin = 0
                }
                ( binding.dinner.layoutParams as ConstraintLayout.LayoutParams).apply {
                    marginStart = 24
                    topMargin = 639
                    marginEnd = 24
                    bottomMargin = 0
                }
            }
            if (Mealdatalunch[0].count == 0) {
                lun=0
                lunch="No results for lunch"
                binding.lunch.visibility=View.GONE
                binding.errorTextBreak.visibility=View.VISIBLE
                binding.errorTextBreak.text = breakfast+"\n"+lunch+"\n"+dinner

                ( binding.dinner.layoutParams as ConstraintLayout.LayoutParams).apply {
                    marginStart=24
                    topMargin=639
                    marginEnd=24
                    bottomMargin=0
                }
            }
            if(Mealdata[0].count == 0 && Mealdatalunch[0].count == 0){
                ( binding.dinner.layoutParams as ConstraintLayout.LayoutParams).apply {
                    marginStart = 24
                    topMargin = 50
                    marginEnd = 24
                    bottomMargin = 0
                }
            }
            if (MealdataDinner[0].count == 0){
                din=0
                dinner="No resluts for dinner"
                binding.dinner.visibility=View.GONE
                binding.errorTextBreak.visibility=View.VISIBLE
                binding.errorTextBreak.text=breakfast+"\n"+lunch+"\n"+dinner

            }


            if(breakfst==1){
                binding.breakfastTitle.text = Mealdata[0].hits[0].recipe.label
                val cal=(Mealdata[0].hits[0].recipe.calories/Mealdata[0].hits[0].recipe.totalWeight)*100+breakRoti.toInt()*104+breakRice.toInt()*136
                binding.brekRoti.text= "Chapatti: "+breakRoti
                if (breakRice!="0")
                binding.brekRice.text= "Rice: "+breakRice+" bowl"
                binding.breakfastQuantity.text = 100.toString() + " gram"
                binding.breakfastCal.text = (cal.roundToInt()* 100.0 / 100.0).toString() + " cal"
                binding.imageView3.load(Mealdata[0].hits[0].recipe.image)
                binding.breakfastSite.setOnClickListener {
                    golink(Mealdata[0].hits[0].recipe.url)
                }
            }
            if(lun==1){
                binding.lunchTitle.text = Mealdatalunch[0].hits[0].recipe.label
                val cal2=(Mealdatalunch[0].hits[0].recipe.calories/Mealdatalunch[0].hits[0].recipe.totalWeight)*100+lunchRoti.toInt()*104+lunchRice.toInt()*136
                binding.lunchRoti.text= "Chapatti: "+ lunchRoti
                if (lunchRice!="0")
                    binding.lunchRice.text= "Rice: "+lunchRice+" bowl"
                binding.lunchQuantity.text = 100.toString() + " gram"
                binding.lunchCal.text =
                    (cal2.roundToInt()* 100.0 / 100.0).toString() + " cal"
                binding.imageView5.load(Mealdatalunch[0].hits[0].recipe.image)
                binding.lunchSite.setOnClickListener {
                    golink(Mealdatalunch[0].hits[0].recipe.url)
                }
            }
            if(din==1){
                binding.dinnerTitle.text = MealdataDinner[0].hits[0].recipe.label
                val cal3=(MealdataDinner[0].hits[0].recipe.calories/MealdataDinner[0].hits[0].recipe.totalWeight)*100+dinnerRoti.toInt()*104+dinnerRice.toInt()*136
                binding.dinnerRoti.text= "Chapatti: "+ dinnerRoti
                if (dinnerRice!="0")
                    binding.dinnerRice.text= "Rice: "+dinnerRice+" bowl"
                binding.dinnerQuantity.text = 100.toString() + " gram"
                binding.dinnerCal.text =
                    (cal3.roundToInt()* 100.0 / 100.0).toString() + " cal"
                binding.imageView7.load(MealdataDinner[0].hits[0].recipe.image)
                binding.dinnerSite.setOnClickListener {
                    golink(MealdataDinner[0].hits[0].recipe.url)
                }
            }
        }
    }
}