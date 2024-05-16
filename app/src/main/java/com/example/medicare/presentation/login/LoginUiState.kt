package com.example.medicare.presentation.login

data class LoginUiState(
    val email:String="",
    val emailErrorMessage:String?=null,
    val password: String="",
    val isPasswordVisible: Boolean=true,
    val passwordErrorMessage: String?=null,
    val acceptPrivacyIsChecked:Boolean=false,
)