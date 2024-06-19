package com.example.medicare.presentation

import com.example.medicare.presentation.navigation.Destination

data class MedicareAppUiState(
    val currentDestination:Destination=Destination.SignUp,
    val selectedIndex:Int=0,
)
