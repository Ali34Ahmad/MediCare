package com.example.medicare.data.model.clinic

import com.example.medicare.data.model.user.Doctor
import com.example.medicare.data.model.date.DaySocket
import com.example.medicare.data.model.date.WorkDay
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

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
        daySockets = emptyList(),
        responsibleDoctor = Doctor(
            firstName = "Ali",
            lastName = "Mansoura"
        ),
        services = emptyList()
    )
}
