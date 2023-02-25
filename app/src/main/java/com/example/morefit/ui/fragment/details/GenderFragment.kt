package com.example.morefit.ui.fragment.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.Datastore.Companion.GENDER
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.launch


class GenderFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_gender, container, false)

        val male = view?.findViewById<MaterialButton>(R.id.first);
        val backButton = view.findViewById<ImageButton>(R.id.gender_back)
        val female = view?.findViewById<MaterialButton>(R.id.second);

        male?.setOnClickListener {
            lifecycleScope.launch {
                datastore.saveUserDetails(GENDER, "Male");
            }

            findNavController().navigate(R.id.action_genderFragment_to_weightFragment)
        }

        female?.setOnClickListener {
            lifecycleScope.launch {
                datastore.saveUserDetails(GENDER, "Female");
            }
            findNavController().navigate(R.id.action_genderFragment_to_weightFragment)
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

}