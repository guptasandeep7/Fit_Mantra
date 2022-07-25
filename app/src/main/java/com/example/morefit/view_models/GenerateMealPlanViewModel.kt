package com.example.morefit.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.morefit.model.Week
import com.example.morefit.model.WeekPlan
import com.example.morefit.repositories.GenerateMealPlanRepository
import com.example.morefit.utils.Response
import kotlinx.coroutines.launch

class GenerateMealPlanViewModel : ViewModel() {
	private var mealPlanResult: MutableLiveData<Response<WeekPlan>> = MutableLiveData()
	val _mealPlanResult: LiveData<Response<WeekPlan>>
		get() = mealPlanResult

	fun submitResult(diet: String, calory: String,timeFrame:String,hash:String,api:String) = viewModelScope.launch {
		mealPlanResult = GenerateMealPlanRepository().genrateMealPlan(diet,calory,timeFrame,hash,api)
		Log.e("data", "submitResult: "+ diet+calory+timeFrame+hash+api)
	}
}
