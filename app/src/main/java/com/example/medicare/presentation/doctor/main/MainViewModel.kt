package com.example.medicare.presentation.doctor.main

import androidx.lifecycle.ViewModel
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.navigation.DoctorDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun updateCurrentScreen(newScreen: DoctorDestination) {
        _uiState.value = _uiState.value.copy(currentScreen = newScreen)
        _uiState.value = _uiState.value.copy(
            selectedBottomNavBarIndex = when (uiState.value.currentScreen) {
                is DoctorDestination.Schedule -> 0
                is DoctorDestination.Profile -> 1
                else -> 0
            }
        )
    }

    fun navigateBackButtonClicked(){
        when(uiState.value.currentScreen){
            is DoctorDestination.Schedule->updateCurrentScreen(DoctorDestination.Schedule)
            is DoctorDestination.Profile->updateCurrentScreen(DoctorDestination.Profile)
            else->updateCurrentScreen(DoctorDestination.Schedule)
        }
    }
}