package com.example.medicare.presentation.vaccinationtable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.data.repositories.VaccineRepository
import com.example.medicare.data.services.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaccinationTableViewModel @Inject constructor(
   private val childRepository: ChildRepository
): ViewModel(){
    private val _uiState= MutableStateFlow(VaccinationTableUiState())
    val uiState=_uiState.asStateFlow()

    var vaccinationTable:List<VaccineTableItem> = emptyList()


    fun updateVaccinationTable(childId: String) {
        updateChildId(childId)
        viewModelScope.launch {
            vaccinationTable=childRepository.getVaccineTable(childId)
        }
    }
    fun updateChildId(childId: String) {
        _uiState.value=_uiState.value.copy(childId=childId)
    }

}