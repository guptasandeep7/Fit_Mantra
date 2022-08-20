package com.example.morefit.utils

import android.app.Activity
import android.view.View
import com.example.morefit.R
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Activity.hideBottomNavigationView() {
    this.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.GONE
}

fun Activity.showBottomNavigationView() {
    this.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
}