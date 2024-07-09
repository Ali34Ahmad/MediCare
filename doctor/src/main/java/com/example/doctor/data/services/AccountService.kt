package com.example.doctor.data.services


import com.example.doctor.data.model.result.AuthState
import com.example.doctor.data.model.user.UserAccount
import kotlinx.coroutines.flow.Flow

interface AccountService {

    val currentUserId: String?

    val isSignedIn: Boolean

    val currentUserAccount: Flow<UserAccount>

    suspend fun login(email: String, password: String) : AuthState

    suspend fun signUp(email: String, password: String) : AuthState

    suspend fun deleteAccount()

    fun signOut()
}
