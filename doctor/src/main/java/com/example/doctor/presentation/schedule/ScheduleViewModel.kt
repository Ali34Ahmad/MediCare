package com.example.doctor.presentation.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.core.composables.generateDaySocketsList
import com.example.doctor.core.getMonthByJavaMonth
import com.example.doctor.core.toFullDate
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.repositories.AppointmentRepository
import com.example.doctor.data.repositories.ClinicRepository
import com.example.doctor.data.services.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val clinicRepository: ClinicRepository,
    private val accountService: AccountService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()

    var appointments = appointmentRepository.appointments
    val appointmentsToNumberOfVisits: MutableList<Int> = mutableListOf()

    val daySockets = generateDaySocketsList()

    init {
        updateClinic()
        updateAppointments()
    }

    fun updateClinic() {
        viewModelScope.launch {
            val clinicId = clinicRepository.getClinicIdByDoctor(accountService.currentUserId)
            Log.v("currentUserId", accountService.currentUserId)
            if(clinicId!=null) {
                val clinic = clinicRepository.getClinicById(clinicId)
                _uiState.update { it.copy(clinic = clinic ?: Clinic()) }
            }
        }
    }

    fun updateBookedDate(date: LocalDate) {
        _uiState.update { it.copy(bookedDate = date) }
        updateSelectedDaySocketIndex(date)
        updateAppointments()
    }

    private fun updateAppointments() {
        viewModelScope.launch {
            appointments =
                appointmentRepository.getAppointmentsByDate(uiState.value.bookedDate.toFullDate())
        }
    }


    private fun updateSelectedDaySocketIndex(date: LocalDate) {
        val index = daySockets.indexOfFirst {
            it.equals(
                date.toFullDate()
            )
        }
        _uiState.update { it.copy(selectedDaySocketIndex = index) }
    }

    fun getNumberOfVisits(userId: String, clinicId: String?) {
        Log.v("NumberOfVisits1", appointmentsToNumberOfVisits.toString())
        viewModelScope.launch {
            Log.v("NumberOfVisitsCor1", appointmentsToNumberOfVisits.toString())

            if (clinicId != null)
                appointmentsToNumberOfVisits.add(
                    //appointmentRepository.getNumberOfAppointments(userId, clinicId)
                    Random.nextInt(1, 10)
                )

            Log.v("NumberOfVisitsCor2", appointmentsToNumberOfVisits.toString())

        }
    }
}