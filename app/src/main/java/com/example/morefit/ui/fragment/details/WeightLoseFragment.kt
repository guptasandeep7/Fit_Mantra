package com.example.morefit.ui.fragment.details

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.utils.DATASTORE_NAME
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.Datastore.Companion.HEIGHT
import com.example.morefit.utils.Datastore.Companion.WEIGHT_LOSE
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch


class WeightLoseFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_weight_lose, container, false)

        val nextButton = view.findViewById<MaterialButton>(R.id.next_btn)
        val weightLose = view.findViewById<TextInputEditText>(R.id.weight_lose_text)
        val backButton = view.findViewById<ImageButton>(R.id.weight_lose_back)

        nextButton.setOnClickListener {

            if(weightLose?.text.toString().toDouble()>2.0 || weightLose?.text.toString().toDouble()<=0.0){
                showDialog()
            }
            else{
                lifecycleScope.launch {
                    datastore.saveUserDetails(WEIGHT_LOSE, weightLose?.text.toString());
                    findNavController().navigate(R.id.action_weightLoseFragment_to_levelFragment)
                }
            }
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }


        return view
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.dialog_unhealthy)
        dialog.show()

    }

}