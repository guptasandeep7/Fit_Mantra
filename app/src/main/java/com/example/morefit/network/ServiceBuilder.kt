package com.example.morefit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    fun buildService(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://morefit.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }
    fun buildService2():ApiInterface {
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/mealplanner/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }
}
