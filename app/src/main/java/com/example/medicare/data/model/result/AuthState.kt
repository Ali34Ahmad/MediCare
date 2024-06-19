package com.example.medicare.data.model.result

import com.google.firebase.auth.FirebaseUser
sealed class AuthState {
    data object Loading : AuthState()
    data object Success : AuthState()
    data class Error(val e: Exception) : AuthState()
}