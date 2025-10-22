package com.example.medicare.presentation.vaccinationtable

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.data.repositories.VaccineRepository
import com.example.medicare.data.services.StorageService
import com.example.medicare.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VaccinationTableViewModel @Inject constructor(
    private val childRepository: ChildRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel(){
    private val _uiState= MutableStateFlow(VaccinationTableUiState())
    val uiState=_uiState.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<Destination.VaccinationTable>()
        updateChildId(args.childId)
        updateVaccinationTable(uiState.value.childId)
    }

    fun updateVaccinationTable(childId: String?) {
        if (childId.isNullOrEmpty()) return
        try {
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        vaccinationTable=childRepository.getVaccineTable(childId)
                    )
                }
                Log.v("VaccinationTable",uiState.value.vaccinationTable.toString())
            }
        }catch (e:Exception){
            Log.e("UpdateVaccinationTable function",e.message?:"Error")
        }

    }
    fun updateChildId(childId: String?) {
        _uiState.value=_uiState.value.copy(childId=childId)
    }

}