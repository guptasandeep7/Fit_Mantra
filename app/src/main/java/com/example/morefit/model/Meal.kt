package com.example.morefit.model

data class Meal(
    val id: Int,
    val imageType: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val title: String
)