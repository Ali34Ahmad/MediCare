package com.example.medicare.presentation.addchild

import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.data.model.date.FullDate

data class AddChildUiState(
    val upNumber: Int?=null,
    val downNumber: Int?=null,
    val number:String="",
    val numberErrorMessage:Int?=null,
    val childFirstName: String="",
    val childFirstNameErrorMessage:Int?=null,
    val childSecondName: String="",
    val childSecondNameErrorMessage:Int?=null,
    val fatherFirstName: String="",
    val fatherFirstNameErrorMessage:Int?=null,
    val fatherSecondName: String="",
    val fatherSecondNameErrorMessage:Int?=null,
    val motherFirstName: String="",
    val motherFirstNameErrorMessage:Int?=null,
    val motherSecondName: String="",
    val motherSecondNameErrorMessage:Int?=null,
    val dateOfBirth: String ="",
    val dateOfBirthErrorMessage:Int?=null,
    val gender: ChooseTabState?=null,
    val genderErrorMessage:Int?=null,
    val acceptPrivacyIsChecked:Boolean=false,
    val showLoadingDialog:Boolean=false,
    val showErrorDialog:Boolean=false,
)
