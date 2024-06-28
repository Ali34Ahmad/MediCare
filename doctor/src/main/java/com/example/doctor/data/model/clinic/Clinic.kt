package com.example.doctor.data.model.clinic

import com.example.doctor.core.enums.Month
import com.example.doctor.data.model.user.Doctor
import com.example.doctor.data.model.date.DaySocket
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.date.WorkDay
import com.google.firebase.firestore.DocumentId

data class Clinic(
    @DocumentId
    val id: String ="",
    val name: String = "",
    val imageUrl: String? = null,
    val workDays: List<WorkDay> = emptyList(),
    val daySockets: List<DaySocket> = emptyList(),
    val responsibleDoctor: Doctor = Doctor(),
    val services : List<String> = emptyList()
){
    private constructor() : this(
        id = "",
        name = "",
        imageUrl = null,
        workDays = emptyList(),
        daySockets = listOf(
            DaySocket(
            date = FullDate(1, Month.JUN, 2024),
            timeSockets = emptyList()
        )
        ),
        responsibleDoctor = Doctor(
            firstName = "Ali",
            lastName = "Mansoura"
        ),
        services = emptyList()
    )
}
