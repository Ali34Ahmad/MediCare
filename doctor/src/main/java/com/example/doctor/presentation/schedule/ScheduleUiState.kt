package com.example.doctor.presentation.schedule

import com.example.doctor.data.model.clinic.Clinic
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import java.time.LocalDate

data class ScheduleUiState (
    val bookedDate: LocalDate = LocalDate.now(),
    val datePickerState: UseCaseState = UseCaseState(),
    val clinic: Clinic = Clinic(),
    val selectedDaySocketIndex:Int=0,
)
