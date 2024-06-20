package com.example.medicare.data.repositories.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.user.User
import com.example.medicare.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    database : FirebaseFirestore,
    auth : FirebaseAuth
) : UserRepository {

    private val usersRef = database.collection(DatabaseCollections.USERS_COLLECTION)
    private val currentUserId = auth.currentUser?.uid

    override suspend fun addNewUser(user: User) {
           currentUserId?.let {usersRef.document(it).set(user).await()}
    }

    override suspend fun getUser(): User? {
        return currentUserId?.let { usersRef.document(it).get().await().toObject(User::class.java) }
    }

    override suspend fun updateUser(user: User) {
        currentUserId?.let {

        }
    }

    override suspend fun getUserById(id: String): User? {
        return usersRef.document(id).get().await().toObject(User::class.java)
    }

    override suspend fun getVisitNumber(id: String): Int {
        return 0
    }
}