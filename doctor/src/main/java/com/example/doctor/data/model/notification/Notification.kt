package com.example.doctor.data.model.notification

import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.date.Time
import com.example.doctor.data.model.user.Doctor
import com.example.doctor.data.model.vaccine.Vaccine
import com.google.firebase.firestore.DocumentId

data class Notification(
    @DocumentId
    val id :String = "",
    val patientName : String = "",
    val userId : String = "",
    val doctorName : String = "",
    val message : String = "",
    val clinic: Clinic = Clinic(),
    val importance : Int = 0,
    val arrivalTime : Pair<FullDate, Time>? = null,
    val visitDate : Pair<FullDate, Time>? = null,
    val vaccine  : Vaccine? = null
    ){
    private constructor() : this(
        "",
        "",
        "",
        "",
        "",
        Clinic(responsibleDoctor = Doctor(firstName = "", lastName = "")),
        0,
        null,
        null,
        null)
}
