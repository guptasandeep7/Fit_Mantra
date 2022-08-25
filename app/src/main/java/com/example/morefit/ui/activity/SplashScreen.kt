package com.example.morefit.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.energybar.database.ContentRoomDatabase
import com.example.morefit.R
import com.example.morefit.ui.fragment.dash.diet.DietFragment
import kotlinx.coroutines.CoroutineScope
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
    companion object{
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