package com.example.morefit.model

data class Advanced(
    val category: String,
    val difficulty: String,
    val gender: String,
    val intensity: String,
    val muscle: String,
    val text_tutorials: List<TextTutorialX>,
    val title: String,
    val video_tutorials: List<String>
)