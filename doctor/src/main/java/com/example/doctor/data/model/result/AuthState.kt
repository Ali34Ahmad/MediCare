package com.example.doctor.data.model.result

sealed class AuthState {
    data object Loading : AuthState()
    data object Success : AuthState()
    data class Error(val e: Exception) : AuthState()
}