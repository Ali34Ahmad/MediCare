package com.example.medicare.presentation.main

import com.example.medicare.presentation.navigation.Destination

data class MainUiState(
    val currentScreen:Destination=Destination.Main(null),
    val selectedBottomNavBarIndex:Int=0,
    val userId:String?=null,
)
