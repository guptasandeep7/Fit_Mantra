package com.example.morefit.ui.fragment.details

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.utils.DATASTORE_NAME
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.Datastore.Companion.HEIGHT
import com.example.morefit.utils.Datastore.Companion.LEVEL
import com.example.morefit.utils.Datastore.Companion.WEIGHT_LOSE
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class LevelFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_level, container, false)

        val beginner = view.findViewById<MaterialButton>(R.id.first)
        val inter = view.findViewById<MaterialButton>(R.id.second)
        val advance = view.findViewById<MaterialButton>(R.id.third)
        val backButton = view.findViewById<ImageButton>(R.id.level_back)

        beginner.setOnClickListener {
            lifecycleScope.launch {
                datastore.saveUserDetails(LEVEL, "beginner")
                datastore.changeLoginState(true)
                findNavController().navigate(R.id.action_levelFragment_to_fitnessGuidelineFragment)
            }
        }

        inter.setOnClickListener {
            lifecycleScope.launch {
                datastore.saveUserDetails(LEVEL, "intermediate")
                datastore.changeLoginState(true)
                findNavController().navigate(R.id.action_levelFragment_to_fitnessGuidelineFragment)
            }
        }

        advance.setOnClickListener {
            lifecycleScope.launch {
                datastore.saveUserDetails(LEVEL, "advanced")
                datastore.changeLoginState(true)
                findNavController().navigate(R.id.action_levelFragment_to_fitnessGuidelineFragment)
            }
        }


        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

}