package com.example.doctor.data.services.impl

import com.example.doctor.data.services.AccountService
import com.example.doctor.data.model.result.AuthState
import com.example.doctor.data.model.user.UserAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth : FirebaseAuth,
    private val database : FirebaseFirestore
) : AccountService {

    override val currentUserId: String?
        get() = auth.currentUser?.uid

    override val isSignedIn: Boolean
        get() = auth.currentUser != null

    override val currentUserAccount: Flow<UserAccount>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener {
                auth ->
                trySend(
                    UserAccount(
                    id = auth.currentUser?.uid ?: "",
                    email = auth.currentUser?.email?: "",
                    )
                )
            }
            auth.addAuthStateListener(listener)
            awaitClose {
                auth.removeAuthStateListener(listener)
            }
        }
    override suspend fun login(email: String, password: String) : AuthState {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            return AuthState.Success
        }catch (e: Exception){
            return AuthState.Error(e)
        }
    }
    override suspend fun signUp(email: String, password: String): AuthState {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            return AuthState.Success
        }catch (e: Exception){
            return AuthState.Error(e)
        }

    }
    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
        AuthState.Success
    }
    override fun signOut(){
        auth.signOut()
    }
}