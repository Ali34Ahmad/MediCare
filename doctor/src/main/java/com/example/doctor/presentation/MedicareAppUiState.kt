package com.example.doctor.presentation

import com.example.doctor.presentation.navigation.Destination

data class MedicareAppUiState(
    val currentDestination: Any = Destination.SignUp,
    val selectedIndex:Int=0,
)
