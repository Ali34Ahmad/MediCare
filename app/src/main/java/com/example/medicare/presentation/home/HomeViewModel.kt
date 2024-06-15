package com.example.medicare.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    val clinics = storageService.clinics
    val appointments = storageService.appointments

    fun updateSelectedClinic(selectedClinicIndex: Int) {
        _uiState.value = _uiState.value.copy(
            selectedClinicIndex = selectedClinicIndex
        )
    }
    fun addClinic(clinic: Clinic){
        viewModelScope.launch {
            storageService.addClinic(clinic)
        }
    }
}