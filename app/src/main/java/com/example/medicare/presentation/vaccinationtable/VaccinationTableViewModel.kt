package com.example.medicare.presentation.vaccinationtable

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VaccinationTableViewModel @Inject constructor(

): ViewModel(){
    private val _uiState= MutableStateFlow(VaccinationTableUiState())
    val uiState=_uiState.asStateFlow()

    fun getVaccinationTable(){

    }

}