package com.example.medicare.data.services

import com.example.medicare.data.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationService {
    val notifications : Flow<List<Notification>>
    suspend fun addNotification(notification : Notification)
}
