package com.example.medicare

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.medicare.core.notification.NotificationHandlerImpl
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class UserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                NotificationHandlerImpl.CHAT_CHANNEL_ID,
                NotificationHandlerImpl.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            //add a description to the channel
            channel.description = getString(R.string.channel_description)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}
