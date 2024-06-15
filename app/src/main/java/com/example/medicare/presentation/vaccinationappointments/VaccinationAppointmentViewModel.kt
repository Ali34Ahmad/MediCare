package com.example.medicare.presentation.vaccinationappointments

import androidx.lifecycle.ViewModel
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VaccinationAppointmentViewModel @Inject constructor(
    private val storageService: StorageService
):ViewModel() {
    private val _uiState= MutableStateFlow(VaccinationAppointmentUiState())
    val uiState=_uiState.asStateFlow()

    val vaccinationAppointments=storageService.appointments

    fun updateSelectedFilter(newFilter:ChooseTabState){
        _uiState.value=_uiState.value.copy(selectedFilter = newFilter)
    }
}