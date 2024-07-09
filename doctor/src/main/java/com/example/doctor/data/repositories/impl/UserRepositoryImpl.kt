package com.example.doctor.data.repositories.impl

import com.example.doctor.core.constants.DatabaseCollections
import com.example.doctor.data.model.user.User
import com.example.doctor.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val database : FirebaseFirestore,
    private val auth : FirebaseAuth
) : UserRepository {

    private val usersRef = database.collection(DatabaseCollections.USERS_COLLECTION)
    private val currentUserId :String? get()= auth.currentUser?.uid

    override suspend fun addNewUser(user: User) {
        currentUserId?.let {
            usersRef.document(it).set(user).await()
        }
    }

    override suspend fun getUser(): User? {
        return currentUserId?.let { usersRef.document(it).get().await().toObject(User::class.java) }
    }

    override suspend fun updateUser(user: User) {
        currentUserId?.let {

        }
    }
}