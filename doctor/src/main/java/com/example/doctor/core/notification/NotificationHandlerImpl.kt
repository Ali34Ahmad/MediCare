package com.example.doctor.core.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.doctor.MainActivity
import com.example.doctor.R

import javax.inject.Inject

class NotificationHandlerImpl @Inject constructor(
    private val context : Context
): NotificationHandler {
    //Get an instance of the NotificationManager
    private val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

    /**Create a notification that will be sent to the specific channel id*/
    override fun showNotification(title:String, message:String){

        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(context,
            1,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, CHAT_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.medicare_logo)
            .setContentIntent(activityPendingIntent)
            .build()

        //using the same id if you have multiple notifications and want to update a specific one
        notificationManager.notify(1,notification)
    }

    companion object{
        const val CHAT_CHANNEL_ID = "default_channel"
        const val CHANNEL_NAME = "medicare_channel"
    }
}