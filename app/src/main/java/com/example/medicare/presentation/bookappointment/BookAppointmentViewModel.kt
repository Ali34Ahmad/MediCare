package com.example.medicare.presentation.bookappointment

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.core.enums.Month
import com.example.medicare.core.toFullDate
import com.example.medicare.data.fake.mmrVaccine
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.data.repositories.AppointmentRepository
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.data.repositories.ClinicRepository
import com.example.medicare.data.repositories.UserRepository
import com.example.medicare.data.repositories.VaccineRepository
import com.example.medicare.data.services.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@ExperimentalMaterial3Api
@HiltViewModel
class BookAppointmentViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val childRepository: ChildRepository,
    private val userRepository: UserRepository,
    private val accountService: AccountService,
    private val clinicRepository: ClinicRepository,
    private val vaccineRepository: VaccineRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookAppointmentUiState())
    val uiState = _uiState.asStateFlow()

    var userName: String? = null
        private set
    var listOfChildren: Flow<List<Child>> = flow { emptyList<Child>() }

    var vaccines: Flow<List<Vaccine>> = vaccineRepository.vaccines

    init {
        /*try {
            listOfChildren=childRepository.children
            viewModelScope.launch {
                userName="${userRepository.getUser()?.firstName}"
            }
        }catch (e:Exception){
            Log.e("Book Appointment ViewModel","Error getting children")
        }*/
    }

    fun updateUserAndChildrenNames(userAndChildrenNames: List<String>) {
        _uiState.value = _uiState.value.copy(userAndChildrenNames = userAndChildrenNames)
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
                withContext(Dispatchers.Main) {
                    uiState.value.pagerState.scrollToPage(uiState.value.pagerState.currentPage + 1)
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

    fun updateNamesMenuVisibilityState() {
        _uiState.value = _uiState.value.copy(
            isNamesMenuVisible = !uiState.value.isNamesMenuVisible
        )
    }

    fun updateChosenNameIndex(chosenNameIndex: Int) {
        _uiState.value = _uiState.value.copy(chosenNameIndex = chosenNameIndex)
    }


    fun getDaySocketIndex(fullDate: FullDate): Int {
        uiState.value.clinic.daySockets.forEachIndexed { index, daySocket ->
            if (daySocket.date == fullDate) return index
        }
        return -1
    }

    fun updateErrorDialogVisibilityState(isVisible:Boolean) {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = !isVisible)
    }

    fun updateLoadingDialogVisibilityState(isVisible:Boolean) {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = isVisible)
    }

    fun bookAppointment() {
        val selectedTimeSocketIndex = uiState.value.chosenTimeSocketIndex
        try {
            if (selectedTimeSocketIndex != null) {

                viewModelScope.launch {
                    updateLoadingDialogVisibilityState(true)
                    appointmentRepository.addAppointment(
                        Appointment(
                            id = "",
                            clinicId = uiState.value.clinic.id,
                            userId = accountService.currentUserId,
                            date = FullDate(
                                year = uiState.value.bookedDate.year,
                                month = getMonthByJavaMonth(uiState.value.bookedDate.month),
                                day = uiState.value.bookedDate.dayOfMonth
                            ),
                            timeSocket = uiState.value.freeTimes[selectedTimeSocketIndex],
                            vaccineId = uiState.value.vaccineId ?: "",
                            clinic = Clinic(
                                name = uiState.value.clinic.name,
                                responsibleDoctor = uiState.value.clinic.responsibleDoctor
                            )
                        )
                    )
                    _uiState.value = _uiState.value.copy(isBookAppointmentIsSuccessful = true)
                    updateLoadingDialogVisibilityState(false)

                }
            } else {
                throw Exception("You have to choose a time for your appointment")
            }
        } catch (e: Exception) {
            updateLoadingDialogVisibilityState(false)
            updateErrorDialogVisibilityState(true)
            Log.e("Book Appointment", e.message ?: "Error")
            _uiState.value = _uiState.value.copy(isBookAppointmentIsSuccessful = false)
        }
    }

    fun getMonthByJavaMonth(month: java.time.Month): Month {
        val values = Month.entries.toTypedArray()
        if (month.ordinal in values.indices) {
            return values[month.ordinal]
        } else
            return values[0]
    }

    fun updateClinic(clinicId: String) {
        try {
            viewModelScope.launch {
                val clinic = clinicRepository.getClinicById(clinicId) ?: Clinic()
                val daySocketIndex = clinic.daySockets.indexOfFirst {
                    it.date == uiState.value.bookedDate.toFullDate()
                }
                _uiState.value = _uiState.value.copy(
                    clinic = clinic,
                    freeTimes = clinic.daySockets[0].timeSockets
                )
                if (clinic.name == "Vaccines")
                    vaccines = vaccineRepository.vaccines
            }
        } catch (e: Exception) {
            Log.e("Update Clinic Event", e.message ?: "Error")
        }
    }

    fun updateCurrentSelectedIndex(index: Int) {
        _uiState.update { it.copy(currentSelectedVaccineIndex = index) }
    }
    fun updateVaccineId(vaccineId:String) {
        _uiState.update { it.copy(vaccineId = vaccineId) }
    }

}