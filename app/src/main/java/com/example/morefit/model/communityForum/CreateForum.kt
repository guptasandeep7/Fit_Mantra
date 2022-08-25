package com.example.morefit.model.communityForum

data class CreateForum(
    val account: Int,
    val content: String,
    val title: String,
    val uri: String? = null
)