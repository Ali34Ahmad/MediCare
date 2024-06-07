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
    private val currentUserId = auth.currentUser!!.uid

    override suspend fun addNewUser(user: User) {
        usersRef.document(currentUserId).set(user).await()
    }

    override suspend fun getUser(): User? {
        return usersRef.document(currentUserId).get().await().toObject(User::class.java)
    }

}