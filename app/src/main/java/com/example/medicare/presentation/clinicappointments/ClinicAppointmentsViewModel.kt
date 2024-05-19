package com.example.medicare.presentation.clinicappointments

import androidx.lifecycle.ViewModel
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ClinicAppointmentsViewModel @Inject constructor(

): ViewModel() {
    private val _uiState= MutableStateFlow(ClinicAppointmentsUiState())
    val uiState=_uiState.asStateFlow()

    fun updateSelectedFilter(newFilter: ChooseTabState){
        _uiState.value=_uiState.value.copy(selectedFilter = newFilter)
    }
}