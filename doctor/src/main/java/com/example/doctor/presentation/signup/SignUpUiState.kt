package com.example.doctor.presentation.signup

import android.graphics.Bitmap
import android.net.Uri
import com.example.doctor.core.composables.ChooseTabState
import com.example.doctor.core.enums.Gender
import com.example.doctor.data.model.result.AuthState

data class SignUpUiState(
    val email:String="",
    val emailErrorMessage:Int?=null,
    val firstName:String="",
    val firstNameErrorMessage:Int?=null,
    val secondName:String="",
    val secondNameErrorMessage:Int?=null,
    val clinicName:String="",
    val clinicNameErrorMessage:Int?=null,
    val password: String="",
    val isPasswordVisible: Boolean=false,
    val passwordErrorMessage: Int?=null,
    val chooseTabState: ChooseTabState?=null,
    val gender: Gender?=null,
    val genderError: Int?=null,
    val acceptPrivacyIsChecked:Boolean=false,
    val showLoadingDialog:Boolean=false,
    val showErrorDialog:Boolean=false,
    val authState:AuthState=AuthState.Loading,

    val doctorImageUri: Uri?=null,
    val doctorErrorMessage:Int?=null,
    val doctorBitmap: Bitmap?=null,
    val clinicImageUri: Uri?=null,
    val clinicErrorMessage:Int?=null,
    val clinicBitmap: Bitmap?=null,
    val showDoctorImageUploader:Boolean=false,
    val showClinicImageUploader: Boolean=false,
    val isSuccessful: Boolean=false,
    val showDoctorProgressBar: Boolean=false,
    val showClinicProgressBar: Boolean=false,
    val doctorImageUrl:String="",
    val clinicImageUrl:String="",
    val speciality: String="",
    val specialityErrorMessage:Int?=null,
    val showSnackBar:Boolean  = false,
    )
