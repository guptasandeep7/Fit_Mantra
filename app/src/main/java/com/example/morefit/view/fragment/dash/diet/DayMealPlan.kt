package com.example.morefit.view.fragment.dash.diet

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.morefit.R
import com.example.morefit.adapter.PageAdapter
import com.example.morefit.adapter.WeekAdapter
import com.example.morefit.databinding.FragmengtDayMealBinding
import com.example.morefit.databinding.FragmentDietBinding
import com.example.morefit.databinding.FragmentDietPlanBinding
import com.google.android.material.tabs.TabLayoutMediator

class DayMealPlan(val position: Int) :Fragment(R.layout.fragmengt_day_meal) {
    private lateinit var binding: FragmengtDayMealBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmengtDayMealBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
//        binding.calBtn.setOnClickListener(this)
//        binding.settingBtn.setOnClickListener(this)
//        Toast.makeText(context, position, Toast.LENGTH_SHORT).show()
//        Log.e("TAG", "onViewCreated: "+position )
    }
}