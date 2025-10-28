package com.example.medicare.presentation.clinicappointments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.core.isUpcoming
import com.example.medicare.data.repositories.AppointmentRepository
import com.example.medicare.data.services.StorageService
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClinicAppointmentsViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ClinicAppointmentsUiState())
    val uiState = _uiState.asStateFlow()

    val appointments = appointmentRepository.appointments

    fun updateSelectedFilter(newFilter: ChooseTabState) {
        _uiState.value = _uiState.value.copy(selectedFilter = newFilter)
    }
}