package com.example.morefit.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.morefit.R
import com.example.morefit.databinding.ActivityProfileBinding
import com.example.morefit.databinding.ActivityUserDetailsBinding
import com.example.morefit.utils.Datastore
import kotlinx.coroutines.launch

class UserDetails : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding
    lateinit var datastore: Datastore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        datastore= Datastore(this@UserDetails)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.nextbutton.setOnClickListener {
                if(!(binding.nameText.text.isNullOrEmpty())) {
                lifecycleScope.launch {
                    datastore.saveUserDetails(Datastore.NAME_KEY, binding.nameText.text.toString())
                    datastore.changeLoginState(true)
                }
                startActivity(Intent(this@UserDetails, MainActivity::class.java))
                finish()
            }
        }
    }
}