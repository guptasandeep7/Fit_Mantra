package com.example.morefit.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.morefit.R
import com.example.morefit.utils.Datastore

class AuthActivity : AppCompatActivity() {
    lateinit var datastore: Datastore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datastore = Datastore(this@AuthActivity)

        setContentView(R.layout.activity_auth)
    }

}