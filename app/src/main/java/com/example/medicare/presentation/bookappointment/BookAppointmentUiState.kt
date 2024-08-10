package com.example.medicare.presentation.bookappointment

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.medicare.core.enums.DayPeriod
import com.example.medicare.core.enums.TimeSocketState
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.Time
import com.example.medicare.data.model.date.TimeSocket
import com.example.medicare.data.model.vaccine.Vaccine
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import java.time.LocalDate

val listOfTimeSockets =
    listOf(
TimeSocket(Time(9, 0, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(9, 15, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(9, 30, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(9, 45, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(10, 0, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(10, 15, DayPeriod.AM), TimeSocketState.FREE),

TimeSocket(Time(10, 30, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(10, 45, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(11, 0, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(11, 15, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(11, 30, DayPeriod.AM), TimeSocketState.FREE),
TimeSocket(Time(11, 45, DayPeriod.AM), TimeSocketState.FREE),

TimeSocket(Time(12, 0, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(12, 15, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(12, 30, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(12, 45, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(1, 0, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(1, 15, DayPeriod.PM), TimeSocketState.FREE),

TimeSocket(Time(1, 30, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(1, 45, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(2, 0, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(2, 15, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(2, 30, DayPeriod.PM), TimeSocketState.FREE),
TimeSocket(Time(2, 45, DayPeriod.PM), TimeSocketState.FREE),

)

@ExperimentalMaterial3Api
data class BookAppointmentUiState (
    val clinic:Clinic=Clinic(),
    val bookedDate:LocalDate=LocalDate.now(),
    val datePickerState: UseCaseState=UseCaseState(),
    val freeTimes:List<TimeSocket> = emptyList(),
    val chosenTimeSocketIndex:Int?=null,
    val selectedDaySocketIndex:Int=0,
    val pagerState: PagerState= PagerState(currentPage=0, pageCount = {4}),
    val userAndChildrenNames:List<String> = emptyList(),
    val chosenNameIndex:Int=0,
    val isNamesMenuVisible:Boolean=false,
    val isBookAppointmentIsSuccessful:Boolean=false,
    val currentSelectedVaccineIndex:Int?=null,
    val vaccineName:String?=null,
    val showLoadingDialog:Boolean=false,
    val showErrorDialog:Boolean=false,
)