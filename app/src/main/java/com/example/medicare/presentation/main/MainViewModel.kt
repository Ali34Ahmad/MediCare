package com.example.medicare.presentation.main

import androidx.lifecycle.ViewModel
import com.example.medicare.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun updateCurrentScreen(newScreen: Destination) {
        _uiState.value = _uiState.value.copy(currentScreen = newScreen)
        _uiState.value = _uiState.value.copy(
            selectedBottomNavBarIndex = when (uiState.value.currentScreen) {
                is Destination.Home -> 0
                is Destination.VaccinationAppointments -> 1
                is Destination.ClinicAppointments -> 2
                is Destination.Children -> 3
                else -> 0
            }
        )
    }

    fun navigateBackButtonClicked(){
        when(uiState.value.currentScreen){
            is Destination.AddChild->updateCurrentScreen(Destination.Children)
            is Destination.BookAppointment->updateCurrentScreen(Destination.Home)
            is Destination.Notification->updateCurrentScreen(Destination.Home)
            else->updateCurrentScreen(Destination.Home)
        }
    }
}