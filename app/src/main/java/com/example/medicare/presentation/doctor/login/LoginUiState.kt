package com.example.medicare.presentation.doctor.login

data class LoginUiState(
    val email:String="",
    val emailErrorMessage:Int?=null,
    val password: String="",
    val isPasswordVisible: Boolean=false,
    val passwordErrorMessage: Int?=null,
    val acceptPrivacyIsChecked:Boolean=false,
    val showLoadingDialog:Boolean=false,
    val showErrorDialog:Boolean=false,
)
