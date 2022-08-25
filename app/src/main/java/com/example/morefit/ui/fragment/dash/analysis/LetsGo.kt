package com.example.morefit.ui.fragment.dash.analysis

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.morefit.R
import com.example.morefit.databinding.FragmentExerciseBinding
import com.example.morefit.databinding.FragmentGymBinding
import com.example.morefit.databinding.FragmentLetsGoBinding

class LetsGo : Fragment() {
    lateinit var binding: FragmentLetsGoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLetsGoBinding.inflate(inflater, container, false)


       return binding.root
    }


}