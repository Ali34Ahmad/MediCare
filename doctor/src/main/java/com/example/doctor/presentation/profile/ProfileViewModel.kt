package com.example.doctor.presentation.profile

import android.icu.util.LocaleData
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.core.composables.generateDaySocketsList
import com.example.doctor.core.enums.Month
import com.example.doctor.core.formatDate
import com.example.doctor.core.getMonthByJavaMonth
import com.example.doctor.core.toFullDate
import com.example.doctor.data.fake.mmrVaccine
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.message.SendMessageDto
import com.example.doctor.data.model.notification.Notification
import com.example.doctor.data.model.vaccine.Vaccine
import com.example.doctor.data.remoteApi.FcmApi
import com.example.doctor.data.repositories.AppointmentRepository
import com.example.doctor.data.repositories.ClinicRepository
import com.example.doctor.data.repositories.VaccineRepository
import com.example.doctor.data.services.AccountService
import com.example.doctor.data.services.NotificationService
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val clinicRepository: ClinicRepository,
    private val accountService: AccountService,
    private val notificationService: NotificationService,
    private val vaccineRepository: VaccineRepository,
    private val fcmApi: FcmApi,
    private val fcm:FirebaseMessaging,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    var appointments = appointmentRepository.appointments
    val appointmentsToNumberOfVisits: MutableList<Int> = mutableListOf()
    val defaultVaccineTable = vaccineRepository.defaultVaccines

    val daySockets = generateDaySocketsList()

    val token= MutableStateFlow<String?>("")

    init {
        viewModelScope.launch {
            token.value=fcm.token.await()
            fcm.subscribeToTopic("chat").await()
        }
        updateClinic()
        updateAppointments()
    }

    fun updateClinic() {
        viewModelScope.launch {
            val clinicId = clinicRepository.getClinicIdByDoctor(accountService.currentUserId)
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

    fun updateErrorDialogVisibilityState(isVisible: Boolean) {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = isVisible)
    }

    fun updateLoadingDialogVisibilityState(isVisible: Boolean) {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = isVisible)
    }

    fun updateSuccessDialogVisibilityState(isVisible: Boolean) {
        _uiState.value =
            _uiState.value.copy(showSuccessDialog = isVisible)
    }


    private fun updateSelectedDaySocketIndex(date: LocalDate) {
        val index = daySockets.indexOfFirst {
            it.equals(
                FullDate(
                    date.dayOfMonth,
                    date.month.getMonthByJavaMonth(),
                    year = date.year
                )
            )
        }
        _uiState.update { it.copy(selectedDaySocketIndex = index) }
    }

    fun getNumberOfVisits(userId: String, clinicId: String?) {
        viewModelScope.launch {
            if (clinicId != null)
                appointmentsToNumberOfVisits.add(
                    appointmentRepository.getNumberOfAppointments(userId,clinicId)
                )
            Log.v("NumberOfVisitsCor", appointmentsToNumberOfVisits.toString())
            Log.v("NumberOfVisitsCor:ClinicId", clinicId.toString())

        }
    }

    fun pushNotification(vaccine: Vaccine) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                updateSuccessDialogVisibilityState(false)
                updateErrorDialogVisibilityState(false)
                updateLoadingDialogVisibilityState(true)
                notificationService.addNotification(
                    Notification(
                        message = "Book your appointment now.",
                        vaccine = vaccine
                    )
                )

                fcmApi.sendMessage(
                    SendMessageDto(
                        "",
                        "Available Vaccine",
                        "${vaccine.name} is available from ${vaccine.availabilityStartDate.formatDate()}-${vaccine.lastAvailableDate.formatDate()}")
                )
                vaccineRepository.addVaccine(vaccine)
                updateLoadingDialogVisibilityState(false)
                updateSuccessDialogVisibilityState(true)
            }
        } catch (e: Exception) {
            updateLoadingDialogVisibilityState(false)
            updateErrorDialogVisibilityState(true)
            updateSuccessDialogVisibilityState(false)
        }


    }

    fun updateCurrentSelectedIndex(index: Int) {
        _uiState.update { it.copy(currentSelectedVaccineIndex = index) }
    }

}