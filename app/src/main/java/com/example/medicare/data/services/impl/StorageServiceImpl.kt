package com.example.medicare.data.services.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.Doctor
import com.example.medicare.data.model.User
import com.example.medicare.data.services.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : StorageService {
    private val collection = firestore.collection(DatabaseCollections.USERS_COLLECTION)
    override suspend fun addNewUser(user: User) {
        collection.add(user).await()
    }
    override suspend fun getUser(userId: String): User? {
        return collection.document(userId).get().await().toObject(User::class.java)
    }

    override val doctors: Flow<Doctor>
        get() = TODO("Not yet implemented")
}
