package com.example.morefit.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.morefit.model.WeekMeal
import com.example.morefit.repositories.GenerateMealPlanRepository
import com.example.morefit.utils.Response
import kotlinx.coroutines.launch

class GenerateMealPlanViewModel() : ViewModel() {
	private var mealPlanResult: MutableLiveData<Response<WeekMeal>> = MutableLiveData()
	val _mealPlanResult: LiveData<Response<WeekMeal>>
		get() = mealPlanResult

	fun submitResult(type:String,q:String,hash:String,api:String,health:String,cuisineType:String,mealType:String,calorie:String) = viewModelScope.launch {
		mealPlanResult = GenerateMealPlanRepository().generateMealPlan(type,q,hash,api,health,cuisineType,mealType,calorie)
		Log.e("data", "submitResult: $type$q$hash$api$health$cuisineType$mealType$calorie")
	}
}
