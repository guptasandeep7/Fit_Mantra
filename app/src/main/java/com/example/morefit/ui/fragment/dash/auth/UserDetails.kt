package com.example.morefit.ui.fragment.dash.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.morefit.R
import com.example.morefit.databinding.FragmentUserDetailsBinding
import com.example.morefit.network.ServiceBuilder
import com.example.morefit.ui.activity.MainActivity
import com.example.morefit.utils.Datastore
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDetails : Fragment() {

    private lateinit var binding: FragmentUserDetailsBinding
    lateinit var datastore: Datastore
    var gender:String="Male"
    lateinit var categories:List<String>
    private var selectedTarget:Int = 0
    var resultcateogry:Float= 0F;

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
        categories = resources.getStringArray(R.array.category_array).toMutableList()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        binding.category.adapter = adapter

        binding.category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                selectedTarget = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
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
                when(selectedTarget){
                    0 ->resultcateogry= 1.2F
                    1 ->resultcateogry= 1.375F
                    2 -> resultcateogry= 1.55F
                    3 -> resultcateogry= 1.725F
                    4 ->resultcateogry= 1.9F
                }
                lifecycleScope.launch {
                    datastore.saveUserDetails(Datastore.CAL.toString(),resultcateogry.toString())
                    datastore.saveUserDetails(Datastore.NAME_KEY, binding.firstNameText.text.toString())
                    datastore.saveUserDetails(Datastore.AGE.toString(),binding.ageText.text.toString())
                    datastore.saveUserDetails(Datastore.HEIGHT.toString(),binding.heightText.text.toString())
                    datastore.saveUserDetails(Datastore.WEIGHT.toString(),binding.weightText.text.toString())
                    datastore.saveUserDetails(Datastore.LAST_NAME_KEY,binding.lastNameText.text.toString())
                    datastore.changeLoginState(true)
                    startActivity(Intent(requireContext(), MainActivity::class.java))

                }
                var age=binding.ageText.text.toString()
               var call= ServiceBuilder.buildService().uploadUserDetails(
                    PhoneNumberFragment.phoneNumber.toLong(),
                    binding.firstNameText.text.toString()+ " " + binding.lastNameText.text.toString(),
                    age.toInt(),
                    gender,
                    binding.heightText.text.toString().toInt(),
                  binding.weightText.text.toString().toInt()
                )
                call.enqueue(object : Callback<com.example.morefit.model.Response?> {
                    override fun onResponse(
                        call: Call<com.example.morefit.model.Response?>,
                        response: Response<com.example.morefit.model.Response?>
                    ) {
                        lifecycleScope.launch {
//                            datastore.saveUserDetails(Datastore.ID, response.body()!!.id.toString())
                            datastore.changeLoginState(true)
                        }
//                        Toast.makeText(requireContext(), response.code().toString(), Toast.LENGTH_SHORT).show()
                        if(response.isSuccessful)
                        {


                    }
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                    }

                    override fun onFailure(call: Call<com.example.morefit.model.Response?>, t: Throwable) {
                        Toast.makeText(requireContext(), t.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }
        return binding.root
    }


}