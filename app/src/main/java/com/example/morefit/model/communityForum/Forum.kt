package com.example.morefit.model.communityForum

data class Forum(
    val account: Int,
    val content: String,
    val id: Int,
    val image: String,
    val is_liked: Boolean,
    val liked_by: List<Int>,
    val title: String,
    val name: String
)