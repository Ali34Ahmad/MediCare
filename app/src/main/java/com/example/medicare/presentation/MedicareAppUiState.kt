package com.example.medicare.presentation

import com.example.medicare.presentation.navigation.Destination

data class MedicareAppUiState(
    val currentDestination: Any =Destination.SignUp,
    val selectedIndex:Int=0,
    val showFloatingActionButton:Boolean=false,
)
