package com.example.morefit.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.morefit.R
import com.example.morefit.utils.Datastore
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {
    lateinit var datastore: Datastore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datastore = Datastore(this@AuthActivity)
        setContentView(R.layout.activity_auth)

    }
}