package com.example.morefit.ui.fragment.dash.diet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.database.ContentRoomDatabase
import com.example.morefit.databinding.FragmentDietBinding
import com.example.morefit.utils.Datastore
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.properties.Delegates

class DietFragment : Fragment(R.layout.fragment_diet), View.OnClickListener {
    private lateinit var binding: FragmentDietBinding
    lateinit var datastore: Datastore
    private val bottomSheetDialog by lazy { BottomSheetDialog(requireContext()) }
    private val mealDb by lazy {
        ContentRoomDatabase.getDatabase(
            requireContext(), CoroutineScope(
                SupervisorJob()
            )
        )
    }

        var count by Delegates.notNull<Int>()
    lateinit var height:String
    lateinit var weight:String
    lateinit var age:String
    lateinit var cal:String
    lateinit var bmi:String
    var calory=0.0
    var bool=0
    var click=0
    companion object {
        lateinit var qBreak: String
        lateinit var qLunch: String
        lateinit var qDinner: String
        lateinit var health: String
        lateinit var calorie: String
        lateinit var breakRoti: String
        lateinit var breakRice: String
        lateinit var lunchRoti: String
        lateinit var lunchRice: String
        lateinit var dinnerRoti: String
        lateinit var dinnerRice: String

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialFadeThrough()
        enterTransition = MaterialFadeThrough()
        reenterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDietBinding.bind(view)
        datastore = Datastore(requireContext())
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
        lifecycleScope.launch {
            height= datastore.getUserDetails("height").toString()
            weight= datastore.getUserDetails("weight").toString()
            age= datastore.getUserDetails("age").toString()
            cal=datastore.getUserDetails("cal").toString()
            if (cal=="null")
                calory=((10 * weight.toDouble()) + (6.25 * height.toDouble()) - (5 * age.toDouble()) + 5)*1.2
            else
                calory=((10 * weight.toDouble()) + (6.25 * height.toDouble()) - (5 * age.toDouble()) + 5)*cal.toDouble()
            bmi=(weight.toDouble() / (height.toDouble()).pow(2.0)).toString()
        }
        if (bmi.toDouble()<18.5){
            bool=1
        }
        if (bmi.toDouble()>18.5 && bmi.toDouble()<24.9 ){
            bool=0
        }
            if (bmi.toDouble()>25 && bmi.toDouble()<29.9 ){
                bool=2
            }

        lifecycleScope.launch {
            count = mealDb.mealDao().getCount()
            if (count > 0) {
                findNavController().navigate(R.id.action_dietFragment_to_dietPlan)
            }
        }
    }

    private fun showBottomSheet() {
        val items =
            layoutInflater.inflate(R.layout.dialog_set_calories, null)
        bottomSheetDialog.setContentView(items)

        val caloryBtn = items.findViewById<MaterialButton>(R.id.enter_btn)
        val ingredientBreak = items.findViewById<TextInputEditText>(R.id.ingredient_break)
        val ingredientLayoutBreak =
            items.findViewById<TextInputLayout>(R.id.ingredient_layout_break)
        val ingredientLunch = items.findViewById<TextInputEditText>(R.id.ingredient_lunch)
        val ingredientLayoutLunch =
            items.findViewById<TextInputLayout>(R.id.ingredient_layout_lunch)
        val ingredientDinner = items.findViewById<TextInputEditText>(R.id.ingredient_dinner)
        val ingredientLayoutDinner =
            items.findViewById<TextInputLayout>(R.id.ingredient_layout_dinner)
        val increase=items.findViewById<MaterialButton>(R.id.increaseCal)
        val maintain=items.findViewById<MaterialButton>(R.id.maintainCal)
        val decrease=items.findViewById<MaterialButton>(R.id.DecCal)

        val plus1 = items.findViewById<Button>(R.id.plus)
        val plus2 = items.findViewById<Button>(R.id.plus2)
        val plus3 = items.findViewById<Button>(R.id.plus3)
        val minus1 = items.findViewById<Button>(R.id.minus)
        val minus2 = items.findViewById<Button>(R.id.minus2)
        val minus3 = items.findViewById<Button>(R.id.minus3)
        val counter1 = items.findViewById<TextView>(R.id.textView8)
        val counter2 = items.findViewById<TextView>(R.id.textView11)
        val counter3 = items.findViewById<TextView>(R.id.textView13)
        val check1 = items.findViewById<CheckBox>(R.id.radioBtn1)
        val check2 = items.findViewById<CheckBox>(R.id.radioBtn2)
        val check3 = items.findViewById<CheckBox>(R.id.radioBtn3)
        var num1 = 0
        var num2 = 0
        var num3 = 0
        if (bool==1){
            increase.elevation=20f
            increase.setBackgroundColor(getResources().getColor(R.color.orange))
            increase.alpha=0.9f
            increase.strokeWidth=0
            increase.setTextColor(getResources().getColor(R.color.white))
            increase.text="Gain Weight(Recommended)"
            click=1
        }
        else if (bool==0){
            maintain.elevation=20f
            maintain.setBackgroundColor(getResources().getColor(R.color.orange))
            maintain.alpha=0.9f
            maintain.strokeWidth=0
            maintain.setTextColor(getResources().getColor(R.color.white))
            maintain.text="Maintain Weight"
            click=2
        }
        else if (bool==2){
            decrease.elevation=20f
            decrease.setBackgroundColor(getResources().getColor(R.color.orange))
            decrease.alpha=0.9f
            decrease.strokeWidth=0
            decrease.setTextColor(getResources().getColor(R.color.white))
            decrease.text="Reduce Weight"
            click=3
        }

        increase.setOnClickListener {
            highlightBtn(increase, decrease, maintain)
        }

        decrease.setOnClickListener {
            highlightBtn(decrease, increase, maintain)
        }

        maintain.setOnClickListener {
            highlightBtn(maintain, decrease, increase)
        }

        bottomSheetDialog.show()
        plus1.setOnClickListener {
            ++num1
            counter1.text = num1.toString()
        }
        plus2.setOnClickListener {
            ++num2
            counter2.text = num2.toString()
        }
        plus3.setOnClickListener {
            ++num3
            counter3.text = num3.toString()
        }
        minus1.setOnClickListener {
            if (num1 == 0) {
                num1 = 0
            } else {
                --num1
            }
            counter1.text = num1.toString()
        }
        minus2.setOnClickListener {
            if (num2 == 0) {
                num2 = 0
            } else {
                --num2
            }
            counter2.text = num2.toString()
        }
        minus3.setOnClickListener {
            if (num3 == 0) {
                num3 = 0
            } else {
                --num3
            }
            counter3.text = num3.toString()
        }

        increase.setOnClickListener {
            click=1
        }
        maintain.setOnClickListener {
            click=2
        }
        decrease.setOnClickListener {
            click=3
        }
        caloryBtn.setOnClickListener {

              if (ingredientBreak.text.toString() == "") {
                ingredientLayoutBreak.helperText = "Enter some ingredient for breakfast"
            } else if (ingredientLunch.text.toString() == "") {
                ingredientLayoutLunch.helperText = "Enter some ingredient for lunch"
            } else if (ingredientDinner.text.toString() == "") {
                ingredientLayoutDinner.helperText = "Enter some ingredient for dinner"
            } else {
                var cal = (num1 + num2 + num3) * 104
                if (check1.isChecked) {
                    cal += 136
                    breakRice = "1"
                } else breakRice = "0"
                if (check2.isChecked) {
                    cal += 136
                    lunchRice = "1"
                } else lunchRice = "0"
                if (check3.isChecked) {
                    cal += 136
                    dinnerRice = "1"
                } else dinnerRice = "0"
                breakRoti = num1.toString()
                lunchRoti = num2.toString()
                dinnerRoti = num3.toString()
                qBreak = ingredientBreak.text.toString()
                qLunch = ingredientLunch.text.toString()
                qDinner = ingredientDinner.text.toString()
                  if(click==1){
                      calorie = (calory - cal+400).toString()
                  }
                  else if(click==2){
                      calorie = (calory - cal).toString()
                  }
                  else if(click==3){
                      calorie = (calory - cal-300).toString()
                  }

                bottomSheetDialog.dismiss()
                findNavController().navigate(R.id.action_dietFragment_to_dietPlan)
            }

        }
    }

    private fun highlightBtn(first: MaterialButton?, second: MaterialButton?, third: MaterialButton?) {
        first?.elevation=20f
        first?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.orange))
//        first.alpha=0.9f
        first?.elevation = 8f
        first?.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

        second?.elevation=20f
        second?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
//        first.alpha=0.9f
        second?.elevation = 0f
        second?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

        third?.elevation=20f
        third?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
//        first.alpha=0.9f
        third?.elevation = 0f
        third?.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.GlutenFree -> {
                    health = "gluten-free"
                    showBottomSheet()

                }
                R.id.ketogenic -> {
                    health = "keto-friendly"
                    showBottomSheet()

                }
                R.id.Vegan -> {
                    health = "vegan"
                    showBottomSheet()

                }
                R.id.Vegetarian -> {
                    health = "vegetarian"
                    showBottomSheet()

                }
                R.id.PeanutFree -> {
                    health = "peanut-free"
                    showBottomSheet()

                }
                R.id.FishFree -> {
                    health = "fish-free"
                    showBottomSheet()

                }
                R.id.NoOilAdded -> {
                    health = "wheat-free"
                    showBottomSheet()

                }
                R.id.SugarFree -> {
                    health = "sugar-free"
                    showBottomSheet()

                }
                R.id.DairyFree -> {
                    health = "dairy-free"
                    showBottomSheet()

                }
                R.id.EggFree -> {
                    health = "egg-free"
                    showBottomSheet()

                }
                R.id.ImmunoSupportive -> {
                    health = "immuno-supportive"
                    showBottomSheet()

                }
                R.id.KidneyFriendly -> {
                    health = "kidney-friendly"
                    showBottomSheet()

                }
                R.id.LowSugar -> {
                    health = "low-sugar"
                    showBottomSheet()

                }
                R.id.MustardFree -> {
                    health = "mustard-free"
                    showBottomSheet()

                }
                R.id.ShellFishFree -> {
                    health = "shellfish-free"
                    showBottomSheet()

                }
                R.id.LowFatAbs -> {
                    health = "low-fat-abs"
                    showBottomSheet()

                }

            }
        }
    }
}