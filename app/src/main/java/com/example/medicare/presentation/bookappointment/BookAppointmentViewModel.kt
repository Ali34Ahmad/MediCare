package com.example.medicare.presentation.bookappointment

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.core.enums.Month
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.services.AccountService
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@ExperimentalMaterial3Api
@HiltViewModel
class BookAppointmentViewModel @Inject constructor(
    private val storageService: StorageService,
    private val accountService: AccountService,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookAppointmentUiState())
    val uiState = _uiState.asStateFlow()

    var userName:String? =null
        private set
    val listOfChildren=storageService.children
    init {
        viewModelScope.launch {
            userName="${storageService.getUser()?.firstName} ${storageService.getUser()?.lastName}"
        }
    }

    fun updateBookedDate(newDate: LocalDate) {
        _uiState.value = _uiState.value.copy(bookedDate = newDate)
    }

    fun updateChosenTimeSocketIndex(newTimeSocketIndex: Int) {
        _uiState.value = _uiState.value.copy(chosenTimeSocketIndex = newTimeSocketIndex)
    }

    fun slideToNextPage() {
        if (uiState.value.pagerState.currentPage in 0..3)
            viewModelScope.launch {
                withContext(Dispatchers.Main){
                    uiState.value.pagerState.scrollToPage(uiState.value.pagerState.currentPage+1)
                }
            }
    }
    fun slideToPreviousPage() {
        if (uiState.value.pagerState.currentPage in 0..3)
            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    uiState.value.pagerState.scrollToPage(uiState.value.pagerState.currentPage - 1)
                }
            }
    }

    fun updateNamesMenuVisibilityState(){
        _uiState.value=_uiState.value.copy(
            isNamesMenuVisible = !uiState.value.isNamesMenuVisible
        )
    }

    fun updateChosenNameIndex(chosenNameIndex: Int) {
        _uiState.value = _uiState.value.copy(chosenNameIndex = chosenNameIndex)
    }

    fun getDaySocketIndex(fullDate: FullDate):Int{
        uiState.value.clinic.daySockets.forEachIndexed { index,daySocket ->
            if (daySocket.date==fullDate) return index
        }
        return -1
    }

    fun bookAppointment(onBookNowButtonClick: () -> Unit) {
        try {
            viewModelScope.launch {
                storageService.addAppointment(
                    Appointment(
                        id="",
                        clinicId = uiState.value.clinic.id,
                        userId = accountService.currentUserId,
                        date = FullDate(
                            year = uiState.value.bookedDate.year,
                            month = getMonthByJavaMonth(uiState.value.bookedDate.month),
                            day = uiState.value.bookedDate.dayOfMonth
                        ),
                        timeSocket = uiState.value.freeTimes[uiState.value.chosenTimeSocketIndex],
                        vaccineId = ""
                    )
                )
            }
            onBookNowButtonClick()
        } catch (e: Exception) {
            Log.e("Book Appointment", e.message ?: "Error")
        }
    }

    fun getMonthByJavaMonth(month: java.time.Month): Month {
        return when (month) {

            java.time.Month.JANUARY -> Month.JAN
            java.time.Month.FEBRUARY -> Month.FEB
            java.time.Month.MARCH -> Month.MAR
            java.time.Month.APRIL -> Month.APR
            java.time.Month.MAY -> Month.MAY
            java.time.Month.JUNE -> Month.JUN
            java.time.Month.JULY -> Month.JUL
            java.time.Month.AUGUST -> Month.AUG
            java.time.Month.SEPTEMBER -> Month.SEP
            java.time.Month.OCTOBER -> Month.OCT
            java.time.Month.NOVEMBER -> Month.NOV
            java.time.Month.DECEMBER -> Month.DEC

        }
    }

    fun updateClinic(clinic: Clinic) {
        _uiState.value=_uiState.value.copy(clinic=clinic)
    }

}