package com.example.morefit.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseViewModel : ViewModel() {

    val backPressed = MutableLiveData<Boolean>()
    val query = MutableLiveData<String>()

    fun setQuery(query: String) {
        this.query.value = query
    }

    fun setBackPressed(value: Boolean) {
        backPressed.value = value
    }

}