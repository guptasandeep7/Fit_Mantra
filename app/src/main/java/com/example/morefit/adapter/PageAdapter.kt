package com.example.morefit.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.morefit.ui.fragment.dash.gym.home.BackFragment
import com.example.morefit.ui.fragment.dash.gym.home.FrontFragment

class PageAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FrontFragment()
            }
            1 -> {
                BackFragment()
            }
            else -> {
                FrontFragment()
            }
        }
    }
}