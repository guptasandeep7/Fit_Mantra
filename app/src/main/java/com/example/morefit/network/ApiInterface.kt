package com.example.morefit.network

import com.example.morefit.model.Data
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("/api/exercise/")
    fun postData(
        @Field("muscle") muscle: String,
        @Field("category") category: String,
        @Field("gender") gender: String="Male"
    ): Call<List<Data>>
}