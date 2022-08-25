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
    private val mealDb by lazy {
        ContentRoomDatabase.getDatabase(
            this, CoroutineScope(
                SupervisorJob()
            )
        )
    }

    companion object {
        var count by Delegates.notNull<Int>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            count = mealDb.mealDao().getCount()
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}