package com.example.morefit.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import com.example.morefit.database.ContentRoomDatabase
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}