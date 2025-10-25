package com.example.medicare.presentation.vaccinationappointments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.core.isUpcoming
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.repositories.AppointmentRepository
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.data.repositories.UserRepository
import com.example.medicare.data.repositories.VaccineRepository
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VaccinationAppointmentViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val userRepository: UserRepository,
    private val childRepository: ChildRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(VaccinationAppointmentUiState())
    val uiState = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val vaccinationAppointments: StateFlow<List<Appointment>> = flow {
        val childrenIds = childRepository.getChildrenIds()
        val currentUserId=userRepository.currentUserId
        val allIds=mutableListOf<String>()
        currentUserId?.let {
            allIds.addAll(childrenIds)
            allIds.add(currentUserId)
        }
        emit(allIds)
    }
        .flatMapLatest { ids ->
            appointmentRepository.vaccineAppointments(ids)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList() // Provide an initial empty state
        )

    fun updateSelectedFilter(newFilter: ChooseTabState) {
        _uiState.value = _uiState.value.copy(selectedFilter = newFilter)
    }
}