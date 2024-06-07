package com.example.medicare.data.services


import com.example.medicare.data.model.user.UserAccount
import kotlinx.coroutines.flow.Flow

interface AccountService {

    val currentUserId: String

    val isSignedIn: Boolean

    val currentUserAccount: Flow<UserAccount>

    suspend fun login(email: String, password: String)

    suspend fun signUp(email: String, password: String)

    suspend fun deleteAccount()

    fun signOut()
}
