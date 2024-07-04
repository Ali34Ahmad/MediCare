package com.example.medicare.presentation

import androidx.lifecycle.ViewModel
import com.example.medicare.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MedicareAppViewModel @Inject constructor(

) : ViewModel() {
    val _uiState = MutableStateFlow(MedicareAppUiState())
    val uiState = _uiState.asStateFlow()

    fun <T : Any> updateCurrentDestination(destination: T) {
        _uiState.value = _uiState.value.copy(
            currentDestination =destination
        )
        when (destination) {
            is Destination.Home -> _uiState.value = _uiState.value.copy(selectedIndex = 0)
            is Destination.VaccinationAppointments -> _uiState.value =
                _uiState.value.copy(selectedIndex = 1)

            is Destination.ClinicAppointments -> _uiState.value =
                _uiState.value.copy(selectedIndex = 2)

            is Destination.Children -> _uiState.value = _uiState.value.copy(selectedIndex = 3)
            else -> _uiState.value = _uiState.value.copy(selectedIndex = 0)
        }
    }


    fun updateFABVisibility(isVisible: Boolean) {
        _uiState.update { it.copy(showFloatingActionButton = isVisible) }
    }
}