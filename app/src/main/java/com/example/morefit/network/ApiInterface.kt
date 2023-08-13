package com.example.morefit.network

import com.example.morefit.model.Data
import com.example.morefit.model.WeekMeal
import com.example.morefit.model.communityForum.CreateForum
import com.example.morefit.model.communityForum.Forum
import okhttp3.ResponseBody
import com.example.morefit.model.communityForum.LikedBy
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


    @GET("post/")
    fun getAllPosts(): Call<List<Forum>>

    @POST("post/")
    fun createPost(@Body body: CreateForum): Call<Forum>
    @GET("/api/recipes/v2")
    fun generateMealPlan(@Query("type") type:String,
                         @Query("q") q:String,
                         @Query("app_id") app_id: String,
                         @Query("app_key") app_key: String,
                         @Query("health") health:String,
                         @Query("cuisineType") cuisineType:String,
                         @Query("mealType") mealType:String,
                         @Query("calories") calories: String):Call<WeekMeal>

    @FormUrlEncoded
    @POST("/api/register/")
    fun uploadUserDetails(
        @Field("Phone no") phoneno:Long,
        @Field("Name")name :String,
        @Field("Age")age:Int,
        @Field("Gender")gender:String,
        @Field("Height")height:Int,
        @Field("Weight")weight:Int
    ):Call<com.example.morefit.model.Response>

    @PATCH("post-like/{id}/")
    fun likePost(
        @Path("id") id: Int,
        @Body likedBy: LikedBy
    ): Call<Forum>


}