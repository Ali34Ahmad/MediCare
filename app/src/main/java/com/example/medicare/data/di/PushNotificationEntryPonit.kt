package com.example.medicare.data.di

import com.example.medicare.core.notification.NotificationHandler
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PushNotificationHandlerEntryPoint {
    fun getPushNotificationHandler(): NotificationHandler
}