package com.example.doctor.presentation.schedule

import androidx.lifecycle.ViewModel
import com.example.doctor.core.getMonthByJavaMonth
import com.example.doctor.core.toFullDate
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.repositories.AppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScheduleUiState())
    val uiState = _uiState.asStateFlow()

    val appointments = appointmentRepository.appointments

    fun updateBookedDate(date: LocalDate) {
        _uiState.update { it.copy(bookedDate = date) }
        updateSelectedDaySocketIndex(date)
        updateAppointments()
    }

    private fun updateAppointments() {
        ///appointments=appointmentRepository.appointments.at(uiState.value.bookedDate.toFullDate())
    }


    private fun updateSelectedDaySocketIndex(date: LocalDate) {
        val index = uiState.value.clinic.daySockets.indexOfFirst {
            it.date.equals(
                date.toFullDate()
            )
        }
        _uiState.update { it.copy(selectedDaySocketIndex = index) }
    }
}