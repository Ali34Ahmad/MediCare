package com.example.medicare.presentation.vaccinationtable

import com.example.medicare.data.model.child.VaccineTableItem


data class VaccinationTableUiState(
    val listOfVaccines:List<VaccineTableItem> = emptyList()
)
