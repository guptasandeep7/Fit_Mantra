package com.example.morefit.utils

import android.app.Activity
import android.util.Log
import android.view.View
import com.example.morefit.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

fun Activity.hideBottomNavigationView() {
    this.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.GONE
}

fun Activity.showBottomNavigationView() {
    this.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
}

fun Long.month(): String {
    val date = Date(this)
    Log.e("EXERCISE_DATE", date.toString())
    return date.toString().substring(4, 7)
}

fun Long.day(): String {
    val date = Date(this)
    return date.toString().substring(8, 10)
}

fun Long.formattedTimeDuration(): String {
    val min = this / 60
    val sec = this % 60

    return "$min min(s) $sec sec(s)"
}

val NOTIFICATION_CHANNEL_ID = "10001"