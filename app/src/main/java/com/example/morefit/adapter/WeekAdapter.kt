package com.example.morefit.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.morefit.view.fragment.dash.diet.DayMealPlan
import com.example.morefit.view.fragment.dash.gym.home.BackFragment
import com.example.morefit.view.fragment.dash.gym.home.FrontFragment

class WeekAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int): Fragment {
        Log.e("TAG", "createFragment: "+position)
        return when (position) {
            0 -> {
                DayMealPlan(position)
            }
            1 -> {
                DayMealPlan(position)
            }
            2->{
                DayMealPlan(position)            }
            3 -> {
                DayMealPlan(position)            }
            4 -> {
                DayMealPlan(position)            }
            5 -> {
                DayMealPlan(position)            }
            6 -> {
                DayMealPlan(position)            }
            else -> {
                DayMealPlan(position)            }
        }
    }
}