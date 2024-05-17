package com.example.medicare.data.model.section

import com.example.medicare.data.model.date.DaySocket
import com.example.medicare.data.model.date.WorkDay

data class Clinic(
    override val id: String,
    override val name: String,
    override val workDays: List<WorkDay>,
    override val daySockets: List<DaySocket>,
    val services : List<String>
):Section()