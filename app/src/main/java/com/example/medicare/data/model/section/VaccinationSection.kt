package com.example.medicare.data.model.section

import com.example.medicare.data.model.Vaccine
import com.example.medicare.data.model.date.DaySocket
import com.example.medicare.data.model.date.WorkDay

data class VaccinationSection(
     override val id: String,
     override val name: String,
     override val workDays: List<WorkDay> = emptyList(),
     override val daySockets: List<DaySocket> = emptyList(),
     val currentVaccines : List<Vaccine> = emptyList()
): Section()
