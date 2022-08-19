package com.example.morefit.model

data class WeekMeal(
    val hits: List<Hit>,
    val _links: Links,
    val count: Int,
    val from: Int,
    val to: Int
)