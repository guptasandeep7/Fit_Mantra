package com.example.morefit.ui.fragment.dash.auth

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.utils.Datastore
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch
import java.sql.Time
import java.util.*

class LandingFragment : Fragment() {
    lateinit var datastore: Datastore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landing, container, false)

//        Toast.makeText(requireContext(),date.toString(), Toast.LENGTH_LONG).show()
        datastore = Datastore(requireContext())

        val startButton = view.findViewById<MaterialButton>(R.id.start_btn)

        startButton.setOnClickListener {
            lifecycleScope.launch {
                if (datastore.isLogin()) {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                } else {
                    findNavController().navigate(R.id.action_landingFragment_to_nameFragment)
                }
            }
        }

        return view
    }
}