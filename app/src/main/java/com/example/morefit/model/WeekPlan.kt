package com.example.morefit.model

data class WeekPlan(
    val week: Week,
    val meals: List<Meal>?=null,
    val nutrients: Nutrients?=null
)