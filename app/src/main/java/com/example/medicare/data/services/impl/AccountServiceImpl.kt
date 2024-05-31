package com.example.medicare.data.services.impl

import com.example.medicare.data.model.user.UserAccount
import com.example.medicare.data.model.result.AuthState
import com.example.medicare.data.services.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth : FirebaseAuth,
) : AccountService {
    override val currentUserId: String
        get() = auth.currentUser?.uid ?: ""
    override val isSignedIn: Boolean
        get() = auth.currentUser != null
    /*
    Override the currentUser property to provide a Flow of User objects.
    This Flow emits a new User object whenever the current user changes.
     */
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
            TODO("make it return user from firestore")
        }

    override suspend fun login(email: String, password: String) : AuthState {
        var state : AuthState = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                state = AuthState.Success
            }.addOnFailureListener {
                state = AuthState.Error(it.message.toString())
            }.await()
        return state
    }

    override suspend fun signUp(email: String, password: String) : AuthState{
        var state : AuthState = AuthState.Loading
       auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
           state = AuthState.Success
       }.addOnFailureListener {
           state = AuthState.Error(it.message.toString())
       }.await()
        return state
    }

    override suspend fun deleteAccount() : AuthState{
        var state : AuthState = AuthState.Loading
        auth.currentUser!!.delete()
            .addOnSuccessListener {
                state = AuthState.Success
            }.addOnFailureListener {
                state = AuthState.Error(it.message.toString())
            }
            .await()
        return state
    }

    override fun signOut(){
        auth.signOut()
    }
}