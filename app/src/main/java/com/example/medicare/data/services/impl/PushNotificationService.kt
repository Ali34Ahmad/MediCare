package com.example.medicare.data.services.impl
import android.util.Log
import com.example.medicare.data.di.PushNotificationHandlerEntryPoint
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.EntryPointAccessors

class PushNotificationServiceImpl : FirebaseMessagingService(){
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Handle new or refreshed FCM registration token
        Log.d(TAG, "Refreshed token: $token")
        // You may want to send this token to your server for further use
    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Handle FCM messages here
        Log.d(TAG, "From: ${message.from}")

        // Check if the message contains data
        message.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: " + message.data)
        }

        // Check if the message contains a notification payload
        message.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            // You can display a notification here if needed
            val entryPoint = EntryPointAccessors.fromApplication(
                applicationContext,
                PushNotificationHandlerEntryPoint::class.java
            )
            val pushNotificationHandler = entryPoint.getPushNotificationHandler()
            pushNotificationHandler.showNotification(title = it.title?:"",message = it.body?:"",)

        }
    }
    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}
