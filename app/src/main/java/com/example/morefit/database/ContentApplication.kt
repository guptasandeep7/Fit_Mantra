package com.example.morefit.database

import android.app.Application
import com.example.morefit.repositories.ContentRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContentApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ContentRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ContentRepo(database.wordDao()) }

    override fun onCreate() {
        super.onCreate()
//        val currentTime = Calendar.getInstance().time
//        val hour = currentTime.toString().substring(11, 13)
//        val min = currentTime.toString().substring(14, 16)
    }
}
