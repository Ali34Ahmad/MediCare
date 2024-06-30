package com.example.medicare.core.notification

interface NotificationHandler {
    fun showNotification(title: String, message: String)
}