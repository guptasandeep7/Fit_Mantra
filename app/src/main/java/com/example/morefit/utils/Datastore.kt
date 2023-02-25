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
        const val ID = "IDKEY"
        const val NAME_KEY = "NAME_KEY"
        const val LAST_NAME_KEY="LAST_NAME"
        val HASH_KEY = stringPreferencesKey("hash_key")
        val USERNAME_KEY = stringPreferencesKey("username_key")
        val DIET_PLAN_KEY = stringPreferencesKey("diet_key")
        val CALORIES_KEY = stringPreferencesKey("calories_key")
        val TIME_FRAME_KEY = stringPreferencesKey("time_frame_key")
        val HEIGHT = stringPreferencesKey("height")
        val LEVEL = stringPreferencesKey("level")
        val WEIGHT_LOSE = stringPreferencesKey("weight_lose")
        val WEIGHT = stringPreferencesKey("weight")
        val GENDER = stringPreferencesKey("gender")
        val AGE = stringPreferencesKey("age")
        val CAL = stringPreferencesKey("cal")
        const val LOGIN_KEY = "LOGIN_KEY"
        val API_KEY = stringPreferencesKey("api_key")
        val STREAK_COUNT = intPreferencesKey("streak_count")
        val LAST_WORKOUT_DATE = longPreferencesKey("last_workout_date")
        val WATER_INTAKE1= stringPreferencesKey("water1")
        val WATER_INTAKE2= stringPreferencesKey("water2")
        val WATER_INTAKE3= stringPreferencesKey("water3")
        val WATER_INTAKE4= stringPreferencesKey("water4")

    }

    suspend fun apiKey() =
        context.datastore.data.first()[API_KEY] ?: "6111f3a8729647caabdb02652bb81c29"

    suspend fun saveUserDetails(key: Preferences.Key<String>, value: String) {
        context.datastore.edit {
            it[key] = value
        }
    }
    suspend fun saveUserDetails(key: String, value: String) {
        val key1 = stringPreferencesKey(key)
        context.datastore.edit {
            it[key1] = value
        }
    }

    suspend fun getUserDetails(key: String): String? {
        val key1 = stringPreferencesKey(key)
        return context.datastore.data.first()[key1]
    }

    suspend fun getLastWorkoutDate(): Long{
        return context.datastore.data.first()[LAST_WORKOUT_DATE]?:0
    }

    suspend fun setLastWorkoutDate(date:Long){
        context.datastore.edit {
            it[LAST_WORKOUT_DATE] = date
        }
    }

    suspend fun getStreakCount(): Int {
        return context.datastore.data.first()[STREAK_COUNT] ?: 0
    }

    suspend fun setStreakCount(count:Int) {
        context.datastore.edit {
            it[STREAK_COUNT] = count
        }
    }
    suspend fun changeLoginState(value: Boolean) {
        val key1 = booleanPreferencesKey(LOGIN_KEY)
       context.datastore.edit {
            it[key1] = value
        }
    }


    suspend fun isLogin(): Boolean {
        val key1 = booleanPreferencesKey(LOGIN_KEY)
        return context.datastore.data.first()[key1] ?: false
    }
}