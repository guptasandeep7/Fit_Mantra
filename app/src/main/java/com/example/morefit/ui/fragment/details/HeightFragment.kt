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
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class HeightFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_height, container, false)


        val nextButton = view?.findViewById<MaterialButton>(R.id.next_btn)
        val height = view?.findViewById<TextInputEditText>(R.id.heightText)
        val backButton = view.findViewById<ImageButton>(R.id.height_back)

        nextButton?.setOnClickListener {
            lifecycleScope.launch {
                datastore.saveUserDetails(HEIGHT, height?.text.toString());
            }

            findNavController().navigate(R.id.action_heightFragment_to_weightLoseFragment)
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

}