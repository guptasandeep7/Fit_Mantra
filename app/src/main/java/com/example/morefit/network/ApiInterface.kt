package com.example.morefit.network

import com.example.morefit.model.Data
import com.example.morefit.model.WeekMeal
import com.example.morefit.model.communityForum.CreateForum
import com.example.morefit.model.communityForum.Forum
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("/api/exercise/")
    fun postData(
        @Field("muscle") muscle: String,
        @Field("category") category: String,
        @Field("gender") gender: String = "Male"
    ): Call<List<Data>>

    @GET("/api/recipes/v2")
    fun generateMealPlan(
        @Query("type") type: String,
        @Query("q") q: String,
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String,
        @Query("health") health: String,
        @Query("cuisineType") cuisineType: String,
        @Query("mealType") mealType: String,
        @Query("calories") calories: String
    ): Call<WeekMeal>

    @GET("post/")
    fun getAllPosts(): Call<List<Forum>>

    @POST("post/")
    fun createPost(@Body body: CreateForum): Call<Forum>
}