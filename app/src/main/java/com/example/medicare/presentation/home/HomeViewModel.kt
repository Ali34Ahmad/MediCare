package com.example.medicare.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.repositories.AppointmentRepository
import com.example.medicare.data.repositories.ClinicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val clinicRepository: ClinicRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    var clinics :Flow<List<Clinic>> = clinicRepository.clinics
    var appointments: Flow<List<Appointment>> = appointmentRepository.appointments

    fun updateSelectedClinic(selectedClinicIndex: Int) {
        _uiState.value = _uiState.value.copy(
            selectedClinicIndex = selectedClinicIndex
        )
    }

    fun addClinic(clinic: Clinic) {
        viewModelScope.launch {
            clinicRepository.addClinic(clinic)
        }
    }
}