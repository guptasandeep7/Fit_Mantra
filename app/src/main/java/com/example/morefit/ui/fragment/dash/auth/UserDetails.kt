package com.example.morefit.ui.fragment.dash.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.morefit.R
import com.example.morefit.databinding.ActivityUserDetailsBinding
import com.example.morefit.databinding.FragmentUserDetailsBinding
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.utils.Datastore
import kotlinx.coroutines.launch

class UserDetails : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    lateinit var datastore: Datastore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datastore= Datastore(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentUserDetailsBinding.inflate(inflater, container, false)
        binding.nextbutton.setOnClickListener {
            if(!(binding.nameText.text.isNullOrEmpty())) {
                lifecycleScope.launch {
                    datastore.saveUserDetails(Datastore.NAME_KEY, binding.nameText.text.toString())
                    datastore.changeLoginState(true)
                }
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        }
        return binding.root
    }


}