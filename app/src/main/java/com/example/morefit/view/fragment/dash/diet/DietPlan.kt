package com.example.morefit.view.fragment.dash.diet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.morefit.R
import com.example.morefit.adapter.PageAdapter
import com.example.morefit.adapter.WeekAdapter
import com.example.morefit.databinding.FragmentDietBinding
import com.example.morefit.databinding.FragmentDietPlanBinding
import com.google.android.material.tabs.TabLayoutMediator

class DietPlan:Fragment(R.layout.fragment_diet_plan) {
    private lateinit var binding: FragmentDietPlanBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentDietPlanBinding.bind(view)
        val pageAdapter = WeekAdapter(this)

        binding.weekPager.adapter = pageAdapter
        TabLayoutMediator(binding.tabProduct, binding.weekPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Monday"
                1 -> tab.text = "Tuesday"
                2 -> tab.text = "Wednesday"
                3 -> tab.text = "Thursday"
                4 -> tab.text = "Friday"
                5 -> tab.text = "Saturday"
                6 -> tab.text = "Sunday"
            }
        }.attach()
        super.onViewCreated(view, savedInstanceState)

//        binding.calBtn.setOnClickListener(this)
//        binding.settingBtn.setOnClickListener(this)
    }
}