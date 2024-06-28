package com.example.doctor.presentation

import androidx.lifecycle.ViewModel
import com.example.doctor.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class MedicareAppViewModel @Inject constructor(

):ViewModel() {
    val _uiState= MutableStateFlow(MedicareAppUiState())
    val uiState=_uiState.asStateFlow()

    fun updateCurrentDestination(destination: Destination){
        _uiState.value=_uiState.value.copy(
            currentDestination = destination
        )
        when(destination){
            is Destination.Schedule->_uiState.value=_uiState.value.copy(selectedIndex = 0)
            is Destination.Profile->_uiState.value=_uiState.value.copy(selectedIndex = 1)
            else-> _uiState.value=_uiState.value.copy(selectedIndex = 0)
        }
    }
}