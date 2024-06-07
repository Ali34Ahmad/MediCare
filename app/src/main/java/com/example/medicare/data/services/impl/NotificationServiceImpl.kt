package com.example.medicare.data.services.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.notification.Notification
import com.example.medicare.data.services.NotificationService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NotificationServiceImpl @Inject constructor(
    database: FirebaseFirestore,
    auth: FirebaseAuth
): NotificationService {
    //get the notification reference
    private val notificationsRef = database.collection(
        DatabaseCollections.NOTIFICATIONS_COLLECTION
    )
    private val currentUserId = auth.currentUser?.uid
    override val notifications: Flow<List<Notification>>
        get() = notificationsRef.whereEqualTo("userId",currentUserId)
            .snapshots()
            .map {
                it.toObjects(Notification::class.java)
            }

    override suspend fun addNotification(notification: Notification) {
        notificationsRef.add(notification).await()
    }
}