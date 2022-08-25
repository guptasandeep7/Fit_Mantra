package com.example.morefit.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.morefit.model.communityForum.Forum
import com.example.morefit.repositories.CommunityForumRepository
import com.example.morefit.sealedClass.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CommunityForumViewModel : ViewModel() {
    private val repository = CommunityForumRepository()

    lateinit var getPostsLiveData: MutableLiveData<Response<List<Forum>>>
    fun getAllPosts() {
        getPostsLiveData = repository.getAllPosts()
    }

    lateinit var createPostLiveData: MutableLiveData<Response<Forum>>
    fun createPost(account: RequestBody,
                   title: RequestBody,
                   content: RequestBody,
                   image: MultipartBody.Part) {
        createPostLiveData = repository.createPost(account, title, content, image)
    }

}