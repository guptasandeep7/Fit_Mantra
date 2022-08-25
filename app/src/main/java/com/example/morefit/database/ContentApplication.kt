package com.example.morefit.database

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import com.example.morefit.broadcastReceiver.MyBroadcastReceiver
import com.example.morefit.repositories.ContentRepo
import com.example.morefit.utils.NotificationService.Companion.CHANNEL_ID
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
        createNotificationChannel()
//        val service = NotificationService(applicationContext)
//        service.showNotification()

        val intent = Intent(applicationContext, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            1,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val time = SystemClock.currentThreadTimeMillis() + 8 * 1000
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)

//        val currentTime = Calendar.getInstance().time
//        val hour = currentTime.toString().substring(11, 13)
//        val min = currentTime.toString().substring(14, 16)

        val intervalTime = 2 * 60 * 60L
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis(),
            intervalTime,
            pendingIntent
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
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

    private fun scheduleNotification(notification: Notification, delay: Int) {
        val notificationIntent = Intent(this, MyBroadcastReceiver::class.java)
        notificationIntent.putExtra(MyBroadcastReceiver.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(MyBroadcastReceiver.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = (getSystemService(Context.ALARM_SERVICE) as AlarmManager)
        alarmManager[AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis] = pendingIntent
    }
}
