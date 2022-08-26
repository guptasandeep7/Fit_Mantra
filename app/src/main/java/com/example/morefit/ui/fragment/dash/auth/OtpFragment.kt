package com.example.morefit.ui.fragment.dash.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.databinding.FragmentOtpBinding
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.utils.Datastore
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

class OtpFragment : Fragment() {

    private var _binding: FragmentOtpBinding? = null
    private val binding get() = _binding!!
    lateinit var datastore: Datastore
    private val phoneNumber = "+91${PhoneNumberFragment.phoneNumber}"

    private var mAuth: FirebaseAuth? = null
    lateinit var edtOTP: TextInputEditText
    lateinit var verifyOTPBtn: Button
    private var verificationId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        datastore = Datastore(requireContext())
        mAuth = FirebaseAuth.getInstance()
        lifecycleScope.launch {
            sendVerificationCode(phoneNumber)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtpBinding.inflate(inflater, container, false)

        //Firebase OTP
        edtOTP = binding.otpEt
        verifyOTPBtn = binding.nextBtn

        verifyOTPBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            try {
                verifyCode(edtOTP.getText().toString())
            }
            catch (e:Exception)
            {
                findNavController().navigate(R.id.action_otpFragment_to_userDetails)
            }
        }

        return binding.root
    }


    private val
            mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onCodeSent(
                s: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(s, forceResendingToken)

                verificationId = s
            }


            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

                val code = phoneAuthCredential.smsCode

                if (code != null) {

                    edtOTP.setText(code)

                    verifyCode(code)
                }
            }


            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(requireActivity(), e.message, Toast.LENGTH_LONG).show()
            }
        }


    private fun sendVerificationCode(number: String) {

        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(number) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity()) // Activity (for callback binding)
            .setCallbacks(mCallBack) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyCode(code: String) {

        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {

        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
//                    Toast.makeText(requireContext(), "Succ", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(requireContext(),MainActivity::class.java))
                    findNavController().navigate(R.id.action_otpFragment_to_userDetails)
                } else {
                    Toast.makeText(
                        requireContext(),
                        task.exception!!.message,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
    }

    private fun View.showKeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    override fun onResume() {
        super.onResume()
        binding.otpEt.requestFocus()
        binding.otpEt.showKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}