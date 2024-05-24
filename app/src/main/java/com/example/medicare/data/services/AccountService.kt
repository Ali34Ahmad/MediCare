package com.example.medicare.data.services


import com.example.medicare.data.model.UserAccount
import com.example.medicare.data.model.result.AuthState
import kotlinx.coroutines.flow.Flow

interface AccountService {

    val currentUserId: String

    val isSignedIn: Boolean

    val currentUserAccount: Flow<UserAccount>
    suspend fun login(email: String, password: String) : AuthState

    suspend fun signUp(email: String, password: String) : AuthState
    suspend fun deleteAccount() : AuthState
    fun signOut()
}