package com.example.medicare.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.repositories.AppointmentRepository
import com.example.medicare.data.repositories.ClinicRepository
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
    var clinicsAppointments: Flow<List<Appointment>> = appointmentRepository.appointments
    var vaccinationsAppointments: Flow<List<Appointment>> = appointmentRepository.appointments

    init {
        collectClinicsFlow()
        collectClinicsAppointmentsFlow()
        collectVaccinationAppointmentsFlow()
    }

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


    private fun collectClinicsFlow() {
        viewModelScope.launch {
            clinics.collectLatest {
                Log.v("clinics", clinics.toString())
            }
        }
    }

    private fun collectClinicsAppointmentsFlow() {
        viewModelScope.launch {
            clinicsAppointments
                .filter { appointments ->
                    appointments.filter {appointment -> appointment.vaccineId.isBlank()  }.isNotEmpty()
                }
                .collectLatest {
                    Log.v("Clinics Appointments", it.toString())
                }
        }
    }

    private fun collectVaccinationAppointmentsFlow() {
        viewModelScope.launch {
            vaccinationsAppointments
                .filter { appointments ->
                    appointments.filter {appointment -> appointment.vaccineId.isNotBlank()  }.isNotEmpty()
                }
                .collectLatest {
                    Log.v("Vaccinations Appointments", it.toString())
                }
        }
    }


}