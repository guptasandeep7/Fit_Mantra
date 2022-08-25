package com.example.morefit.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.morefit.model.communityForum.CreateForum
import com.example.morefit.model.communityForum.Forum
import com.example.morefit.repositories.CommunityForumRepository
import com.example.morefit.sealedClass.Response

class CommunityForumViewModel : ViewModel() {
    private val repository = CommunityForumRepository()

    lateinit var getPostsLiveData: MutableLiveData<Response<List<Forum>>>
    fun getAllPosts() {
        getPostsLiveData = repository.getAllPosts()
    }

    lateinit var createPostLiveData: MutableLiveData<Response<Forum>>
    fun createPost(body: CreateForum) {
        createPostLiveData = repository.createPost(body)
    }

}