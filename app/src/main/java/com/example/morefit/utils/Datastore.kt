package com.example.morefit.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

const val DATASTORE_NAME = "FitMe"
val Context.datastore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)

class Datastore(val context: Context) {

    companion object {
        val HASH_KEY = stringPreferencesKey("hash_key")
        val USERNAME_KEY = stringPreferencesKey("username_key")
        val DIET_PLAN_KEY = stringPreferencesKey("diet_key")
        val CALORIES_KEY = stringPreferencesKey("calories_key")
        val TIME_FRAME_KEY = stringPreferencesKey("time_frame_key")
        val API_KEY = stringPreferencesKey("api_key")
    }

    suspend fun apiKey() = context.datastore.data.first()[API_KEY] ?: "6111f3a8729647caabdb02652bb81c29"

    suspend fun saveUserDetails(key:String,value: String) {
        val key1 = stringPreferencesKey(key)
        context.datastore.edit {
            it[key1] = value
        }
    }
    suspend fun getUserDetails(key: String): String? {
        val key1 = stringPreferencesKey(key)
        return context.datastore.data.first()[key1]
    }

}