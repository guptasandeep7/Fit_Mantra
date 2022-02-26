package com.example.morefit.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.morefit.adapter.PageAdapter
import com.example.morefit.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val pageAdapter = PageAdapter(this)

        binding.viewpagerProduct.adapter = pageAdapter

        TabLayoutMediator(binding.tabProduct, binding.viewpagerProduct) { tab, position ->
            when (position) {
                0 -> tab.text = "Front"
                1 -> tab.text = "Back"
            }
        }.attach()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}