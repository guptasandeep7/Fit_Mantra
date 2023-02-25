package com.example.morefit.ui.fragment.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.utils.DATASTORE_NAME
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.Datastore.Companion.NAME_KEY
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class NameFragment : Fragment() {

    lateinit var datastore:Datastore
    lateinit var name:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        datastore= Datastore(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_name, container, false)

        val nextButton = view.findViewById<MaterialButton>(R.id.name_next_btn)
        val backButton = view.findViewById<ImageButton>(R.id.name_back)
        val name = view.findViewById<TextInputEditText>(R.id.nameText)

        name.requestFocus()
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT)


        nextButton.setOnClickListener {
            findNavController().navigate(R.id.action_nameFragment_to_ageFragment)
            lifecycleScope.launch {
                datastore.saveUserDetails(NAME_KEY, name?.text.toString());
            }

        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }

}