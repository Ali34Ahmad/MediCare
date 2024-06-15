package com.example.medicare.presentation.notification

import com.example.medicare.data.model.notification.Notification

data class NotificationUiState(
    val notificationList:List<Notification> = emptyList()
)