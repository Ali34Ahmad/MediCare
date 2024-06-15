package com.example.medicare.presentation.home

import com.example.medicare.data.model.user.Doctor


data class HomeUiState(
    val responsibleDoctor: Doctor?=null,
    val selectedClinicIndex:Int=0,
)