package com.example.morefit.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.morefit.model.WeekMeal
import com.example.morefit.network.ServiceBuilder
import com.example.morefit.utils.Response
import retrofit2.Call
import retrofit2.Callback

class GenerateMealPlanRepository() {
	private val liveData = MutableLiveData<Response<WeekMeal>>()

	fun generateMealPlan(type:String,q:String,hash:String,api:String,health:String,cuisineType:String,mealType:String,calorie:String): MutableLiveData<Response<WeekMeal>> {
		val call = ServiceBuilder.buildService2().generateMealPlan(type,q,hash,api,health,cuisineType,mealType,calorie)
		Log.e("datarepo", "submitResult: $type$q$hash$api$health$cuisineType$mealType$calorie")
		call.enqueue(object : Callback<WeekMeal> {
			override fun onResponse(
				call: Call<WeekMeal>,
				response: retrofit2.Response<WeekMeal>
			) {
				when {
					response.isSuccessful ->{
						liveData.postValue(Response.Success(response.body()!!))
					}

					else -> {
						liveData.postValue(Response.Error(response.message()))
						Log.e("else", "onFailure: "+response.body() )
					}
				}
			}

			override fun onFailure(call: Call<WeekMeal>, t: Throwable) {
				val message =t.message
				liveData.postValue(message?.let { Response.Error(it) })
				Log.e("faliue", "onFailure: "+t.message )
			}
		})

		return liveData
	}
}