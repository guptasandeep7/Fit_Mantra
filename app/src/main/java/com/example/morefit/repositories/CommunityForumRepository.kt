package com.example.morefit.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.morefit.model.communityForum.CreateForum
import com.example.morefit.model.communityForum.Forum
import com.example.morefit.network.ServiceBuilder
import com.example.morefit.sealedClass.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Body

class CommunityForumRepository {

    private val getPostsLiveData = MutableLiveData<Response<List<Forum>>>()
    fun getAllPosts(): MutableLiveData<Response<List<Forum>>> {
        val call = ServiceBuilder.forumBuildService().getAllPosts()
        call.enqueue(object : Callback<List<Forum>> {

            override fun onResponse(
                call: Call<List<Forum>>,
                response: retrofit2.Response<List<Forum>>
            ) {
                if (response.isSuccessful)
                    getPostsLiveData.postValue(Response.Success(response.body()))

                else getPostsLiveData.postValue(Response.Error(response.message()))
                Log.e("API_CALL", response.body().toString())
            }

            override fun onFailure(call: Call<List<Forum>>, t: Throwable) {
                Log.e("RESPONSE", t.message.toString())
                getPostsLiveData.postValue(Response.Error(t.message.toString()))
            }
        })
        return getPostsLiveData
    }

    private val createPostLiveData = MutableLiveData<Response<Forum>>()
    fun createPost(body: CreateForum): MutableLiveData<Response<Forum>> {
        val call = ServiceBuilder.forumBuildService().createPost(body)
        call.enqueue(object : Callback<Forum> {

            override fun onResponse(
                call: Call<Forum>,
                response: retrofit2.Response<Forum>
            ) {
                if (response.isSuccessful)
                    createPostLiveData.postValue(Response.Success(response.body()))

                else createPostLiveData.postValue(Response.Error(response.message()))

                Log.e("API_CALL", response.body().toString())
            }

            override fun onFailure(call: Call<Forum>, t: Throwable) {
                Log.e("RESPONSE", t.message.toString())
                createPostLiveData.postValue(Response.Error(t.message.toString()))
            }
        })
        return createPostLiveData
    }
}