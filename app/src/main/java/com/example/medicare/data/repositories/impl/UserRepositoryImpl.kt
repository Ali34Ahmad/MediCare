package com.example.medicare.data.repositories.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.user.User
import com.example.medicare.data.repositories.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    database : FirebaseFirestore,
    private val auth : FirebaseAuth
) : UserRepository {

    private val usersRef = database.collection(DatabaseCollections.USERS_COLLECTION)
    override val currentUserId :String?
        get() = auth.currentUser?.uid

    override suspend fun addNewUser(user: User) {
        currentUserId?.let {usersRef.document(it).set(user).await()}
    }

    override suspend fun getUser(): User? {
        return currentUserId?.let { usersRef.document(it).get().await().toObject(User::class.java) }
    }

    override suspend fun updateUser(user: User) {
        currentUserId?.let {
            usersRef.document(it).set(user).await()
        }
    }

    override suspend fun getUserById(id: String): User? {
        return usersRef.document(id).get().await().toObject(User::class.java)
    }

    override suspend fun getVisitNumber(id: String): Int {
        return 0
    }
    override val user: Flow<User?>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                auth.currentUser?.uid?.let { id ->
                    usersRef.document(id).get().addOnCompleteListener { task ->
                        if (task.isSuccessful ) {
                            trySend(task.result.toObject(User::class.java))
                        } else {
                            trySend(null) // or emit an error
                        }
                    }
                } ?: trySend(null) // User not authenticated
            }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }.flowOn(Dispatchers.IO) // Ensure Firestore operations happen on a background thread
}