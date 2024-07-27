package com.example.doctor.presentation.profile

import com.example.doctor.data.model.clinic.Clinic
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import java.time.LocalDate

data class ProfileUiState(
    val clinic: Clinic = Clinic(),
    val bookedDate: LocalDate = LocalDate.now(),
    val datePickerState: UseCaseState = UseCaseState(),
    val selectedDaySocketIndex: Int=0,
    val currentSelectedVaccineIndex: Int?=null,
)