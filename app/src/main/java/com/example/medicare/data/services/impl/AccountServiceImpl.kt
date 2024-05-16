package com.example.medicare.data.services.impl

import com.example.medicare.data.model.User
import com.example.medicare.data.services.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth : FirebaseAuth
) : AccountService {
    override val currentUserId: String
        get() = auth.currentUser?.uid ?: ""
    override val isSignedIn: Boolean
        get() = auth.currentUser != null

    /*
    Override the currentUser property to provide a Flow of User objects.
    This Flow emits a new User object whenever the current user changes.
     */
    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener {
                auth ->
                trySend(
                    User(
                    auth.currentUser?.uid ?: "",
                    auth.currentUser?.email?: ""
                    )
                )
            }
            auth.addAuthStateListener(listener)
            awaitClose {
                auth.removeAuthStateListener(listener)
            }
        }

    override suspend fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signUp(email: String, password: String) {
       auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override fun signOut() {
        auth.signOut()
    }
}