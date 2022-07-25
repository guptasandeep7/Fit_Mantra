package com.example.morefit.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.morefit.model.Week
import com.example.morefit.model.WeekPlan
import com.example.morefit.network.ServiceBuilder
import com.example.morefit.utils.Response
import retrofit2.Call
import retrofit2.Callback
import kotlin.math.log

class GenerateMealPlanRepository {
	private val liveData = MutableLiveData<Response<WeekPlan>>()

	fun genrateMealPlan(diet: String, calory: String,timeFrame:String,hash:String,api:String): MutableLiveData<Response<WeekPlan>> {
		val call = ServiceBuilder.buildService2().generateMealPlan(diet,calory,timeFrame,hash,api)
		Log.e("data", "submitResult: "+ diet+calory+timeFrame+hash+api)
		call.enqueue(object : Callback<WeekPlan> {
			override fun onResponse(
				call: Call<WeekPlan>,
				response: retrofit2.Response<WeekPlan>
			) {
				when {
					response.isSuccessful ->
						liveData.postValue(Response.Success(response.body()!!))
					else -> liveData.postValue(Response.Error(response.message()))
				}
			}

			override fun onFailure(call: Call<WeekPlan>, t: Throwable) {
				val message =t.message
//					if (t.message == "Unable to resolve host \"late-entry.azurewebsites.net\": No address associated with hostname")
//						"No Internet connection! Please connect to the Internet first!" else t.message + " Please try again"

				liveData.postValue(message?.let { Response.Error(it) })
			}
		})

		return liveData
	}
}