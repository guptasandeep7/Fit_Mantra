package com.example.morefit.view_models

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.morefit.model.communityForum.CreateForum
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

    lateinit var uploadImageMutableLiveData : MutableLiveData<String>
    fun uploadPic(uri: Uri, account: Int) {
        uploadImageMutableLiveData = repository.uploadPic(uri, account)
    }

    lateinit var createPostLiveData: MutableLiveData<Response<Forum>>
    fun createPost(body: CreateForum) {
        createPostLiveData = repository.createPost(body)
    }

    lateinit var likeLiveData: MutableLiveData<Response<Forum>>
    fun likePost(id: Int, account: Int) {
        likeLiveData = repository.likePost(id, account)
    }
}