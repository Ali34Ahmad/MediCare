package com.example.medicare.presentation.addchild

import com.example.dispensary.ui.composables.ChooseTabState

data class AddChildUiState(
    val upNumber: Int?=null,
    val downNumber: Int?=null,
    val number:String="",
    val numberErrorMessage:String?=null,
    val childFirstName: String="",
    val childFirstNameErrorMessage:String?=null,
    val childSecondName: String="",
    val childSecondNameErrorMessage:String?=null,
    val fatherFirstName: String="",
    val fatherFirstNameErrorMessage:String?=null,
    val fatherSecondName: String="",
    val fatherSecondNameErrorMessage:String?=null,
    val motherFirstName: String="",
    val motherFirstNameErrorMessage:String?=null,
    val motherSecondName: String="",
    val motherSecondNameErrorMessage:String?=null,
    val dateOfBirth: String="",
    val dateOfBirthErrorMessage:String?=null,
    val gender: ChooseTabState?=null,
    val genderErrorMessage:String?=null,
    val acceptPrivacyIsChecked:Boolean=false,
)
