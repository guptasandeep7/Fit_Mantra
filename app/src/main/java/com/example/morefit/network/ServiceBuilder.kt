package com.example.morefit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    fun buildService(): ApiInterface = Retrofit.Builder()
        .baseUrl("https://morefit.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    fun buildService2(): ApiInterface = Retrofit.Builder()
        .baseUrl("https://api.edamam.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    fun forumBuildService(): ApiInterface = Retrofit.Builder()
        .baseUrl("https://fitmantra.azurewebsites.net/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)
}
