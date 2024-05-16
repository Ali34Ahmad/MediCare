package com.example.medicare.data.services

import com.example.medicare.data.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {

    val currentUserId: String

    val isSignedIn: Boolean

    val currentUser: Flow<User>
    suspend fun login(email: String, password: String)

    suspend fun signUp(email: String, password: String)
    suspend fun deleteAccount()
    fun signOut()
}