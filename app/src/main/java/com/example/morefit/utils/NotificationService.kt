package com.example.morefit.utils

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.morefit.R
import com.example.morefit.ui.activity.MainActivity
import kotlin.random.Random

class NotificationService(
    private val context: Context
) {
    companion object {
        const val CHANNEL_ID = "CHANNEL_ID"
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(isDrinkNotification: Boolean = true) {
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val msg = if (isDrinkNotification) "This is a reminder for you to drink water!"
        else "Time to take a break and eat your lunch"

        val title = if (isDrinkNotification) "Hydration Reminder" else "Food Reminder"

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_frame_3)
            .setContentTitle(title)
            .setContentText(msg)
            .setContentIntent(activityPendingIntent)
            .build()

        val randomId = Random(System.currentTimeMillis()).nextInt(1000)
        notificationManager.notify(randomId, notification)
    }
}