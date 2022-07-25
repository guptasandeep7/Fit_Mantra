package com.example.morefit.network

import com.example.morefit.model.Data
import com.example.morefit.model.Week
import com.example.morefit.model.WeekPlan
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("/api/exercise/")
    fun postData(
        @Field("muscle") muscle: String,
        @Field("category") category: String,
        @Field("gender") gender: String="Male"
    ): Call<List<Data>>

    @GET("generate")
    fun generateMealPlan(@Query("diet") diet: String,@Query("targetCalories") calory: String,
                         @Query("timeFrame") timeFrame: String,@Query("hash") hash: String,
                         @Query("apiKey") api: String):Call<WeekPlan>
}