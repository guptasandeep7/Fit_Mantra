package com.example.morefit.ui.fragment.dash.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.morefit.R
import com.example.morefit.databinding.ActivityUserDetailsBinding
import com.example.morefit.databinding.FragmentUserDetailsBinding
import com.example.morefit.network.ServiceBuilder
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.utils.Datastore
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class UserDetails : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    lateinit var datastore: Datastore
    var gender:String="Male"
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
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId==R.id.male){
                gender="Male"
            }
            else
            {
                gender="Female"
            }
        }
        binding.nextbutton.setOnClickListener {
            if(!(binding.firstNameText.text.isNullOrEmpty())) {
                lifecycleScope.launch {
                    datastore.saveUserDetails(Datastore.NAME_KEY, binding.firstNameText.text.toString())
                    datastore.saveUserDetails(Datastore.LAST_NAME_KEY,binding.lastNameText.text.toString())
                    datastore.changeLoginState(true)
                }
               var call= ServiceBuilder.buildService().uploadUserDetails(
                    PhoneNumberFragment.phoneNumber,
                    binding.firstNameText.text.toString()+ " " + binding.lastNameText.text.toString(),
                    binding.ageText.text.toString(),
                    gender,
                    binding.heightText.text.toString(),
                  binding.weightText.text.toString()
                )
                call.enqueue(object : Callback<ResponseBody?> {
                    override fun onResponse(
                        call: Call<ResponseBody?>,
                        response: Response<ResponseBody?>
                    ) {
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                    }

                    override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                        Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }
        return binding.root
    }


}