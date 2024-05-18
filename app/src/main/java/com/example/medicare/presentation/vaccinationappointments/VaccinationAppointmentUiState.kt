package com.example.medicare.presentation.vaccinationappointments

import com.example.dispensary.ui.composables.ChooseTabState

data class VaccinationAppointmentUiState(
    val selectedFilter:ChooseTabState=ChooseTabState.First
)