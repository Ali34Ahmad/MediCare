package com.example.medicare.ui.presentation.signup

import com.example.dispensary.ui.composables.ChooseTabState

data class SignUpUiState(
    val email:String="",
    val emailErrorMessage:String?=null,
    val firstName:String="",
    val firstNameErrorMessage:String?=null,
    val secondName:String="",
    val secondNameErrorMessage:String?=null,
    val password: String="",
    val passwordIsHidden: Boolean=true,
    val passwordErrorMessage: String?=null,
    val gender: ChooseTabState?=null,
    val genderError: String?=null,
    val acceptPrivacyIsChecked:Boolean=false,
)