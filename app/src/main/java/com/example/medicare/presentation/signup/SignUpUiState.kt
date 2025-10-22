package com.example.medicare.presentation.signup

import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.core.enums.Gender
import com.example.medicare.data.model.result.AuthState

data class SignUpUiState(
    val email:String="",
    val emailErrorMessage:Int?=null,
    val firstName:String="",
    val firstNameErrorMessage:Int?=null,
    val secondName:String="",
    val secondNameErrorMessage:Int?=null,
    val password: String="",
    val isPasswordVisible: Boolean=false,
    val passwordErrorMessage: Int?=null,
    val chooseTabState: ChooseTabState?=null,
    val gender: Gender?=null,
    val genderError: Int?=null,
    val acceptPrivacyIsChecked:Boolean=false,
    val showLoadingDialog:Boolean=false,
    val showErrorDialog:Boolean=false,
    val signUpState:AuthState=AuthState.Loading,
)
