package com.example.morefit.ui.fragment.dash.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentPhoneNumberBinding
import com.example.morefit.utils.Datastore
import kotlinx.coroutines.launch

class PhoneNumberFragment : Fragment() {
    private var _binding: FragmentPhoneNumberBinding? = null
    private val binding get() = _binding!!
    lateinit var datastore: Datastore

    companion object{
        lateinit var  phoneNumber:String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datastore = Datastore(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhoneNumberBinding.inflate(inflater, container, false)

        binding.otpBtn.setOnClickListener {
            val phone = binding.phoneEt.text.toString()
            if(phone.length == 10){
                phoneNumber = phone
//                lifecycleScope.launch {
//                    datastore.saveToDatastore(Datastore.PHONE_NUMBER,phone,requireContext())
//                }
                findNavController().navigate(R.id.action_phoneNumberFragment_to_otpFragment)
            }
            else{
                binding.phoneLayout.helperText = "Enter valid phone number"
            }
        }
        return binding.root
    }

    private fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onResume() {
        super.onResume()
        binding.phoneEt.requestFocus()
        binding.phoneEt.showKeyboard()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}