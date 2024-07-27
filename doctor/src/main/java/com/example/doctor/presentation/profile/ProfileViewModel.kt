package com.example.doctor.presentation.profile

import android.icu.util.LocaleData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.core.enums.Month
import com.example.doctor.core.getMonthByJavaMonth
import com.example.doctor.core.toFullDate
import com.example.doctor.data.fake.mmrVaccine
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.notification.Notification
import com.example.doctor.data.model.vaccine.Vaccine
import com.example.doctor.data.repositories.AppointmentRepository
import com.example.doctor.data.repositories.ClinicRepository
import com.example.doctor.data.services.AccountService
import com.example.doctor.data.services.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val clinicRepository: ClinicRepository,
    private val accountService: AccountService,
    private val notificationService: NotificationService,
): ViewModel() {
    private val _uiState= MutableStateFlow(ProfileUiState())
    val uiState=_uiState.asStateFlow()

    var appointments=appointmentRepository.appointments
    val appointmentsToNumberOfVisits:MutableList<Int> = mutableListOf()
    val defaultVaccineTable: Flow<List<Vaccine>> = flow {  }
    init {
        updateClinic()
        getNumberOfVisits()
    }
    fun updateClinic(){
        viewModelScope.launch {
            val clinicId=clinicRepository.getClinicIdByDoctor(accountService.currentUserId)
            val clinic=clinicRepository.getClinicById(clinicId?:"")
            _uiState.update { it.copy(clinic=clinic?: Clinic()) }
        }
    }
    fun getClinic(){

    }

    fun updateBookedDate(date: LocalDate){
        _uiState.update { it.copy(bookedDate = date) }
        updateSelectedDaySocketIndex(date)
        updateAppointments()
    }
    private fun updateAppointments(){
        //appointments=appointmentRepository.appointments.at(uiState.value.bookedDate.toFullDate())
    }


    private fun updateSelectedDaySocketIndex(date: LocalDate){
        val index=uiState.value.clinic.daySockets.indexOfFirst { it.date.equals(
            FullDate(
                date.dayOfMonth,
                date.month.getMonthByJavaMonth(),
                year = date.year
            )
        ) }
        _uiState.update { it.copy(selectedDaySocketIndex = index) }
    }

    fun getNumberOfVisits() {
        val job=viewModelScope.async {
            val job = viewModelScope.async {
                appointments=appointmentRepository.getAppointmentsByDate(uiState.value.bookedDate.toFullDate())

                appointments.map { appointments ->
                    appointments.forEach { appt->
                        appointmentsToNumberOfVisits.add(
                            appointmentRepository.getNumberOfAppointments(appt.userId)
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            job.await()
        }
    }

    fun pushNotification() {
        viewModelScope.launch {
            notificationService.addNotification(
                Notification(
                    message="This is notification.",
                    vaccine= mmrVaccine
                )
            )
        }

    }

    fun updateCurrentSelectedIndex(index: Int) {
        _uiState.update { it.copy(currentSelectedVaccineIndex = index) }
    }

}