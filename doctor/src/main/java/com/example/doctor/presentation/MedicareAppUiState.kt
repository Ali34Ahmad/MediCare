package com.example.doctor.presentation

import com.example.doctor.presentation.navigation.Destination

data class MedicareAppUiState(
    val currentDestination: Destination = Destination.SignUp,
    val selectedIndex:Int=0,
)
