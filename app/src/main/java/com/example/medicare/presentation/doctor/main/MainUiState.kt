package com.example.medicare.presentation.doctor.main

import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.navigation.DoctorDestination

data class MainUiState(
    val currentScreen:DoctorDestination=DoctorDestination.Schedule,
    val selectedBottomNavBarIndex:Int=0,
    val selectedClinicId:String="",
)
