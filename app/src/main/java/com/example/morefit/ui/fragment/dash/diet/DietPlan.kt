package com.example.morefit.ui.fragment.dash.diet

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.adapter.MealBreakAdapter
import com.example.morefit.adapter.MealDinnerAdapter
import com.example.morefit.adapter.MealLunchAdapter
import com.example.morefit.databinding.FragmentDietPlanBinding
import com.example.morefit.model.WeekMeal
import com.example.morefit.model.database.meal
import com.example.morefit.model.item_model
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.breakRice
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.breakRoti
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.calorie
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.dinnerRice
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.dinnerRoti
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.health
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.lunchRice
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.lunchRoti
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.qBreak
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.qDinner
import com.example.morefit.ui.fragment.dash.diet.DietFragment.Companion.qLunch
import com.example.morefit.utils.Response
import com.example.morefit.view_models.GenerateMealPlanViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import com.example.morefit.database.ContentRoomDatabase
import com.example.morefit.utils.Datastore
import kotlin.math.roundToInt
import kotlin.random.Random


class DietPlan : Fragment(R.layout.fragment_diet_plan), View.OnClickListener {
    private lateinit var binding: FragmentDietPlanBinding
    lateinit var Mealdata: MutableList<WeekMeal>
    lateinit var Mealdatalunch: MutableList<WeekMeal>
    lateinit var MealdataDinner: MutableList<WeekMeal>
    lateinit var meal: MutableList<item_model>
    lateinit var meal1: MutableList<item_model>
    lateinit var meal2: MutableList<item_model>
    lateinit var datastore: Datastore
    private val mealBreakAdapter = MealBreakAdapter()
    private val mealLunchAdapter = MealLunchAdapter()
    private val mealDinnerAdapter = MealDinnerAdapter()
    private val mealDb by lazy {
        ContentRoomDatabase.getDatabase(
            requireContext(),
            CoroutineScope(SupervisorJob())
        )
    }
    var breakWgTot = 0.0
    var lunchWgTot = 0.0
    var dinnerWgTot = 0.0
    var index1=0
    var index2=0
    var index3=0
    var calorieBreak:String="0.0"
    var calorieLunch: String = "0.0"
    var calorieDinner: String ="0.0"
    var water1 = 0.0
    var water2 = 0.0
    var water3 = 0.0
    var water4 = 0.0
    private val generateMealPlanViewModel by lazy { ViewModelProvider(this)[GenerateMealPlanViewModel::class.java] }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDietPlanBinding.bind(view)
        binding.radioButton.setOnClickListener(this)
        binding.radioButton2.setOnClickListener(this)
        binding.radioButton3.setOnClickListener(this)
        Mealdata = mutableListOf()
        Mealdatalunch = mutableListOf()
        MealdataDinner = mutableListOf()
        datastore = Datastore(requireContext())
        meal = mutableListOf()
        meal1 = mutableListOf()
        meal2 = mutableListOf()
        binding.recyclerview1.adapter = mealBreakAdapter
        binding.recyclerview2.adapter = mealLunchAdapter
        binding.recyclerview3.adapter = mealDinnerAdapter

        binding.progressBar.visibility = View.VISIBLE
        binding.breakfast.visibility = View.GONE
        binding.lunch.visibility = View.GONE
        binding.dinner.visibility = View.GONE
        binding.waterTraker.visibility = View.GONE
        binding.waterTraker2.visibility = View.GONE
        binding.waterTraker3.visibility = View.GONE
        binding.waterTraker4.visibility = View.GONE

        binding.textView17.visibility = View.VISIBLE
        binding.textView18.visibility = View.VISIBLE
        binding.textView19.visibility = View.VISIBLE


        lifecycleScope.launch {
            water1= datastore.getUserDetails("water1")?.toDouble() ?: 0.0
            water2= datastore.getUserDetails("water2")?.toDouble() ?: 0.0
            water3= datastore.getUserDetails("water3")?.toDouble() ?: 0.0
            water4= datastore.getUserDetails("water4")?.toDouble() ?: 0.0
        }
        binding.imageView8.setOnClickListener {
            water1 = water1 + 0.25
            binding.textView21.text = (water1).toString() + " L"
            lifecycleScope.launch {
                datastore.saveUserDetails("water1",water1.toString())
            }
            if (water1 == 1.0) {
                binding.textView29.setTextColor(getResources().getColor(R.color.green))
            }
        }
        binding.imageView9.setOnClickListener {
            water2 = water2 + 0.25
            binding.textView24.text = (water2).toString() + " L"
            lifecycleScope.launch {
                datastore.saveUserDetails("water2",water2.toString())
            }
            if (water2 == 1.0) {
                binding.textView30.setTextColor(getResources().getColor(R.color.green))
            }
        }
        binding.imageView10.setOnClickListener {
            water3 = water3 + 0.25
            binding.textView26.text = (water3).toString() + " L"
            lifecycleScope.launch {
                datastore.saveUserDetails("water3",water3.toString())
            }
            if (water3 == 1.0) {
                binding.textView31.setTextColor(getResources().getColor(R.color.green))
            }
        }
        binding.imageView11.setOnClickListener {
            water4 = water4 + 0.25
            binding.textView28.text = (water4).toString() + " L"
            lifecycleScope.launch {
                datastore.saveUserDetails("water4",water4.toString())
            }
            if (water4 == 1.0) {
                binding.textView32.setTextColor(getResources().getColor(R.color.green))
            }
        }
        binding.regenerate.setOnClickListener {
            val customView = layoutInflater.inflate(R.layout.dialog_meal, null)
            val builder = MaterialAlertDialogBuilder(requireContext()).apply {
                setView(customView)
                background = ColorDrawable(Color.TRANSPARENT)
                setCancelable(false)
            }
            val dialog = builder.show()
            customView.findViewById<MaterialTextView>(R.id.dialogMessage)
            val regenerateMeal = customView.findViewById<MaterialButton>(R.id.positiveBtn)
            val cancel = customView.findViewById<MaterialButton>(R.id.cancel)
            regenerateMeal.setOnClickListener {
                lifecycleScope.launch {
                    mealDb.mealDao().deleteMealData()
                    datastore.saveUserDetails("water1","0")
                    datastore.saveUserDetails("water2","0")
                    datastore.saveUserDetails("water3","0")
                    datastore.saveUserDetails("water4","0")

                }
                findNavController().navigateUp()
                dialog.dismiss()
            }
            cancel.setOnClickListener {
                dialog.dismiss()
            }
        }
        lifecycleScope.launch {
            val cout: Int = mealDb.mealDao().getCount()

            if (cout == 0) {
                calorieBreak = ((calorie.toDouble() * 0.3).roundToInt() * 100.0 / 100.0).toString()
                calorieLunch = ((calorie.toDouble() * 0.4).roundToInt() * 100.0 / 100.0).toString()
                calorieDinner =((calorie.toDouble() * 0.3).roundToInt() * 100.0 / 100.0).toString()
                var count = 0
                generateMealPlanViewModel.submitResult(
                    "public",
                    qBreak,
                    "16a70afb",
                    "96079c81218f8debd87360747fd41368",
                    health,
                    "Indian",
                    "Breakfast",
                    calorieBreak
                )
                generateMealPlanViewModel._mealPlanResult.observe(viewLifecycleOwner) {
                    if (it is Response.Success) {
                        Mealdata.add(it.data!!)
                        if (it.data.count != 0) {
                            if(it.data.count>20){
                                index1= Random(System.nanoTime()).nextInt(20-1 - 0 + 1) + 0
                            }
                            else
                            index1= Random(System.nanoTime()).nextInt(it.data.count-1 - 0 + 1) + 0
                            lifecycleScope.launch {

                                Toast.makeText(context, index1.toString(), Toast.LENGTH_SHORT).show()
                                mealDb.mealDao().addMealData(
                                    meal(
                                        "breakfast",
                                        it.data.hits[index1].recipe.image,
                                        it.data.hits[index1].recipe.label,
                                        (((it.data.hits[index1].recipe.totalWeight / it.data.hits[index1].recipe.calories) * calorieBreak.toDouble()).roundToInt() * 100.0 / 100.0).toString()
                                        ,calorieBreak,breakRoti,
                                        breakRice,
                                        it.data.hits[index1].recipe.url,
                                    )
                                )
                            }
                            breakWgTot =
                                ((it.data.hits[index1].recipe.totalWeight / it.data.hits[index1].recipe.calories) * calorieBreak.toDouble()).roundToInt() * 100.0 / 100.0
                            meal.add(
                                item_model(
                                    it.data.hits[index1].recipe.image,
                                    it.data.hits[index1].recipe.label,
                                    breakWgTot.toString()+" gram",
                                    calorieBreak + " cal"
                                )
                            )
                            if (breakRoti != "0") {
                                meal.add(
                                    item_model(
                                        getUrl(R.drawable.roti),
                                        "Roti",
                                        breakRoti + " serving",
                                        (104 * breakRoti.toInt()).toString() + " cal"
                                    )
                                )
                            }
                            if (breakRice != "0") {
                                meal.add(
                                    item_model(
                                        getUrl(R.drawable.rice),
                                        "Rice",
                                        "1 bowl",
                                        "136 cal"
                                    )
                                )
                            }
                        }
                        count++
                        call(count)
                    } else it.errorMessage?.let {
                        Log.e("mssg", "showBottomSheet: error")
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
                generateMealPlanViewModel.submitResult(
                    "public",
                    qLunch,
                    "16a70afb",
                    "96079c81218f8debd87360747fd41368",
                    health,
                    "Indian",
                    "Lunch",
                    calorieLunch
                )
                generateMealPlanViewModel._mealPlanResult.observe(viewLifecycleOwner) {
                    if (it is Response.Success) {
                        Mealdatalunch.add(it.data!!)
                        if (it.data.count != 0) {
                            if(it.data.count>20){
                                index2= Random(System.nanoTime()).nextInt(20-1 - 0 + 1) + 0
                            }
                            else
                                index2= Random(System.nanoTime()).nextInt(it.data.count-1 - 0 + 1) + 0
                            lifecycleScope.launch {
                                mealDb.mealDao().addMealData(
                                    meal(
                                        "lunch",
                                        it.data.hits[index2].recipe.image,
                                        it.data.hits[index2].recipe.label,
                                        (((it.data.hits[index2].recipe.totalWeight / it.data.hits[index2].recipe.calories) * calorieLunch.toDouble()).roundToInt() * 100.0 / 100.0).toString()
                                        ,calorieLunch ,
                                        lunchRoti,
                                        lunchRice,
                                        it.data.hits[index2].recipe.url
                                    )
                                )
                            }
                            lunchWgTot =
                                ((it.data.hits[index2].recipe.totalWeight / it.data.hits[index2].recipe.calories) * calorieLunch.toDouble()).roundToInt() * 100.0 / 100.0
                            meal1.add(
                                item_model(
                                    it.data.hits[index2].recipe.image,
                                    it.data.hits[index2].recipe.label,
                                    lunchWgTot.toString()+" gram",
                                    calorieLunch + " cal"
                                )
                            )
                            if (lunchRoti != "0") {
                                meal1.add(
                                    item_model(
                                        getUrl(R.drawable.roti),
                                        "Roti",
                                        lunchRoti + " serving",
                                        (104 * lunchRoti.toInt()).toString() + " cal"
                                    )
                                )
                            }
                            if (lunchRice != "0") {
                                meal1.add(
                                    item_model(
                                        getUrl(R.drawable.rice),
                                        "Rice",
                                        "1 bowl",
                                        "136 cal"
                                    )
                                )
                            }
                        }
                        count++
                        call(count)
                    } else it.errorMessage?.let {
                        Log.e("mssg", "showBottomSheet: error")
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
                generateMealPlanViewModel.submitResult(
                    "public",
                    qDinner,
                    "16a70afb",
                    "96079c81218f8debd87360747fd41368",
                    health,
                    "Indian",
                    "Dinner",
                    calorieDinner
                )
                generateMealPlanViewModel._mealPlanResult.observe(viewLifecycleOwner) {
                    if (it is Response.Success) {
                        MealdataDinner.add(it.data!!)
                        if (it.data.count != 0) {
                            if(it.data.count>20){
                                index3= Random(System.nanoTime()).nextInt(20-1 - 0 + 1) + 0
                            }
                            else
                                index3= Random(System.nanoTime()).nextInt(it.data.count-1 - 0 + 1) + 0
                            lifecycleScope.launch {
                                mealDb.mealDao().addMealData(
                                    meal(
                                        "dinner",
                                        it.data.hits[index3].recipe.image,
                                        it.data.hits[index3].recipe.label,
                                        (((it.data.hits[index3].recipe.totalWeight / it.data.hits[index3].recipe.calories) * calorieDinner.toDouble()).roundToInt() * 100.0 / 100.0).toString()
                                        ,calorieDinner,
                                        dinnerRoti,
                                        dinnerRice,
                                        it.data.hits[index3].recipe.url
                                    )
                                )
                            }
                            dinnerWgTot=
                            ((it.data.hits[index3].recipe.totalWeight / it.data.hits[index3].recipe.calories) * calorieDinner.toDouble()).roundToInt() * 100.0 / 100.0
                            meal2.add(
                                item_model(
                                    it.data.hits[index3].recipe.image,
                                    it.data.hits[index3].recipe.label,
                                    dinnerWgTot.toString()+" gram",
                                    calorieDinner + " cal"
                                )
                            )
                            if (dinnerRoti != "0") {
                                meal2.add(
                                    item_model(
                                        getUrl(R.drawable.roti),
                                        "Roti",
                                        dinnerRoti + " serving",
                                        (104 * dinnerRoti.toInt()).toString() + " cal"
                                    )
                                )
                            }
                            if (dinnerRice != "0") {
                                meal2.add(
                                    item_model(
                                        getUrl(R.drawable.rice),
                                        "Rice",
                                        "1 bowl",
                                        "136 cal"
                                    )
                                )
                            }
                        }
                        count++
                        call(count)
                    } else it.errorMessage?.let {
                        Log.e("mssg", "showBottomSheet: error")
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                val mealData = mealDb.mealDao().getMealData()
                binding.progressBar.visibility = View.GONE
                binding.breakfast.visibility = View.VISIBLE
                binding.lunch.visibility = View.VISIBLE
                binding.dinner.visibility = View.VISIBLE
                binding.waterTraker.visibility = View.VISIBLE
                binding.waterTraker2.visibility = View.VISIBLE
                binding.waterTraker3.visibility = View.VISIBLE
                binding.waterTraker4.visibility = View.VISIBLE
                binding.textView21.text=water1.toString()+" L"
                binding.textView24.text=water2.toString()+" L"
                binding.textView26.text=water3.toString()+" L"
                binding.textView28.text=water4.toString()+" L"

                mealData.forEach {
                    when (it.id) {
                        "breakfast" -> {
                            binding.textView17.visibility = View.GONE
                            meal.add(
                                item_model(
                                    it.image, it.title, it.serving+" gram", it.cal + " cal"
                                )
                            )
                            if (it.roti != "0") {
                                meal.add(
                                    item_model(
                                        getUrl(R.drawable.roti), "Roti",
                                        it.roti + " serving",
                                        (104 * it.roti.toInt()).toString() + " cal"
                                    )
                                )
                            }
                            if (it.rice != "0") {
                                meal.add(
                                    item_model(
                                        getUrl(R.drawable.rice),
                                        "Rice",
                                        "1 bowl",
                                        "136 cal"
                                    )
                                )
                            }
                            val url = it.url
                            binding.breakfast.setOnClickListener {
                                golink(url)
                            }
                            binding.textView12.text =
                                (it.roti.toInt() * 104 + it.rice.toInt() * 136 + it.cal.toFloat()).toString() + " cal"
                            mealBreakAdapter.updateMealList(meal)
                        }
                        "lunch" -> {
                            binding.textView18.visibility = View.GONE
                            meal1.add(
                                item_model(
                                    it.image, it.title,it.serving+" gram" , it.cal + " cal"
                                )
                            )
                            if (it.roti != "0") {
                                meal1.add(
                                    item_model(
                                        getUrl(R.drawable.roti), "Roti",
                                        it.roti + " serving",
                                        (104 * it.roti.toInt()).toString() + " cal"
                                    )
                                )
                            }
                            if (it.rice != "0") {
                                meal1.add(
                                    item_model(
                                        getUrl(R.drawable.rice),
                                        "Rice",
                                        "1 bowl",
                                        "136 cal"
                                    )
                                )
                            }
                            val url = it.url
                            binding.lunch.setOnClickListener {
                                golink(url)
                            }
                            binding.textView13.text =
                                (it.roti.toInt() * 104 + it.rice.toInt() * 136 + it.cal.toFloat()).toString() + " cal"
                            mealLunchAdapter.updateMealList(meal1)
                        }
                        "dinner" -> {
                            binding.textView19.visibility = View.GONE
                            meal2.add(
                                item_model(
                                    it.image, it.title, it.serving+" gram", it.cal + " cal"
                                )
                            )
                            if (it.roti != "0") {
                                meal2.add(
                                    item_model(
                                        getUrl(R.drawable.roti), "Roti",
                                        it.roti + " serving",
                                        (104 * it.roti.toInt()).toString() + " cal"
                                    )
                                )
                            }
                            if (it.rice != "0") {
                                meal2.add(
                                    item_model(
                                        getUrl(R.drawable.rice),
                                        "Rice",
                                        "1 bowl",
                                        "136 cal"
                                    )
                                )
                            }
                            val url = it.url
                            binding.dinner.setOnClickListener {
                                golink(url)
                            }
                            binding.textView14.text =
                                (it.roti.toInt() * 104 + it.rice.toInt() * 136 + it.cal.toFloat()).toString() + " cal"
                            mealDinnerAdapter.updateMealDinnerList(meal2)
                        }
                    }
                }

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
                    } else {
                        binding.radioButton2.isChecked = false
                        binding.radioButton3.isChecked = false
                        binding.container2.setBackgroundResource(R.drawable.orange)
                        binding.container3.setBackgroundResource(R.drawable.orange)
                        binding.container1.setBackgroundResource(R.drawable.orange)
                    }
                }
                R.id.radioButton2 -> {
                    if (binding.radioButton2.isChecked) {
                        binding.radioButton3.isChecked = false
                        binding.radioButton.isChecked = true
                        binding.container2.setBackgroundResource(R.drawable.black_gradient)
                        binding.container3.setBackgroundResource(R.drawable.orange)
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                    } else {
                        binding.radioButton.isChecked = true
                        binding.radioButton3.isChecked = false
                        binding.container2.setBackgroundResource(R.drawable.orange)
                        binding.container3.setBackgroundResource(R.drawable.orange)
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                    }
                }
                R.id.radioButton3 -> {
                    if (binding.radioButton3.isChecked) {
                        binding.radioButton.isChecked = true
                        binding.radioButton2.isChecked = true
                        binding.container3.setBackgroundResource(R.drawable.black_gradient)
                        binding.container2.setBackgroundResource(R.drawable.black_gradient)
                        binding.container1.setBackgroundResource(R.drawable.black_gradient)
                    } else {
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

        val uri: Uri = Uri.parse(sourceUrl)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    private fun call(count: Int) {
        var breakfst = 1
        var din = 1
        var lun = 1
        if (count == 3) {
            binding.progressBar.visibility = View.GONE
            binding.breakfast.visibility = View.VISIBLE
            binding.lunch.visibility = View.VISIBLE
            binding.dinner.visibility = View.VISIBLE
            binding.waterTraker.visibility = View.VISIBLE
            binding.waterTraker2.visibility = View.VISIBLE
            binding.waterTraker3.visibility = View.VISIBLE
            binding.waterTraker4.visibility = View.VISIBLE
            water1=0.0
            water2=0.0
            water3=0.0
            water4=0.0
            if (Mealdata[0].count == 0) {
                breakfst = 0
                binding.recyclerview1.visibility = View.GONE
                binding.textView17.visibility = View.VISIBLE
            }
            if (Mealdatalunch[0].count == 0) {
                lun = 0
                binding.recyclerview2.visibility = View.GONE
                binding.textView18.visibility = View.VISIBLE

            }
            if (MealdataDinner[0].count == 0) {
                din = 0
                binding.recyclerview3.visibility = View.GONE
                binding.textView19.visibility = View.VISIBLE

            }


            if (breakfst == 1) {
                binding.textView17.visibility = View.GONE
                binding.breakfast.setOnClickListener {
                    golink(Mealdata[0].hits[0].recipe.url)
                }
                binding.textView12.text =
                    (breakRoti.toInt() * 104 + breakRice.toInt() * 136 + calorieBreak.toDouble()).toString() + " cal"
                mealBreakAdapter.updateMealList(meal)
            }
            if (lun == 1) {
                binding.textView18.visibility = View.GONE
                binding.lunch.setOnClickListener {
                    golink(Mealdatalunch[0].hits[0].recipe.url)
                }
                binding.textView13.text =
                    (lunchRoti.toInt() * 104 + lunchRice.toInt() * 136 + calorieLunch.toDouble()).toString() + " cal"
                mealLunchAdapter.updateMealList(meal1)
            }
            if (din == 1) {
                binding.textView19.visibility = View.GONE
                binding.dinner.setOnClickListener {
                    golink(MealdataDinner[0].hits[0].recipe.url)
                }
                binding.textView14.text =
                    (dinnerRoti.toInt() * 104 + dinnerRice.toInt() * 136 + calorieDinner.toDouble()).toString() + " cal"
                mealDinnerAdapter.updateMealDinnerList(meal2)
            }
        }
    }

    private fun getUrl(resourceId: Int): String {
        return Uri.parse(
            "android.resource://" + R::class.java.getPackage().getName() + "/" + resourceId
        ).toString()
    }

}