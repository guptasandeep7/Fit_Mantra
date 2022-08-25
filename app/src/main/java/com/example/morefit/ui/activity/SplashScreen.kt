package com.example.morefit.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.morefit.R
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.datastore
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {
    lateinit var datastore: Datastore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datastore = Datastore(this@SplashScreen)
//        lifecycleScope.launch {
//            if(datastore.isLogin()) {
//                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
//                finish()
//            } else {
////                startActivity(Intent(this@SplashScreen, UserDetails::class.java))
////                finish()
//            }
            startActivity(Intent(this@SplashScreen, AuthActivity::class.java))
            finish()
        }

    }
