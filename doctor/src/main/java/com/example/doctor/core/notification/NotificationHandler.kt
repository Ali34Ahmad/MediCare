package com.example.doctor.core.notification

interface NotificationHandler {
    fun showNotification(title: String, message: String)
}