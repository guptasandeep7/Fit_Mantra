package com.example.morefit.ui.activity

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.morefit.R
import com.example.morefit.broadcastReceiver.MyBroadcastReceiver
import com.example.morefit.utils.Datastore
import com.example.morefit.utils.NotificationService

class AuthActivity : AppCompatActivity() {
    lateinit var datastore: Datastore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        datastore = Datastore(this@AuthActivity)

        setContentView(R.layout.activity_auth)

        createNotificationChannel()
        val service = NotificationService(this)
        service.showNotification()

//        val intent = Intent(this, MyBroadcastReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(
//            this,
//            1,
//            intent,
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
//        )
//        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

//        val time = System.currentTimeMillis() + 8 * 1000
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)

//        alarmManager.setInexactRepeating(
//            AlarmManager.RTC_WAKEUP,
//            System.currentTimeMillis(),
//            50 * 60 * 1000L,
//            pendingIntent
//        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationService.CHANNEL_ID,
                "Channel Name",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "descriptionText"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}