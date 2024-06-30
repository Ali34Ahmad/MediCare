package com.example.doctor.data.services

import com.example.doctor.data.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationService {

    val notifications : Flow<List<Notification>>
    suspend fun addNotification(notification : Notification)

}
