package com.example.medicare.presentation.clinicappointments

import com.example.dispensary.ui.composables.ChooseTabState

data class ClinicAppointmentsUiState(
    val selectedFilter: ChooseTabState = ChooseTabState.First
)
