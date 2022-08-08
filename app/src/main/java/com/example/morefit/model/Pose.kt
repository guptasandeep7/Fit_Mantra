package com.example.morefit.model

data class Pose(
    val english_name: String,
    val sanskrit_name: String,
    val yoga_description: String,
    val image_url: String,
    val yoga_instruction: List<String>
)