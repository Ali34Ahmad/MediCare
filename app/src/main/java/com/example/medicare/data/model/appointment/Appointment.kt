package com.example.medicare.data.model.appointment

import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.date.TimeSocket
import com.google.firebase.firestore.DocumentId

data class Appointment(
    @DocumentId
    val id: String,
    val clinicId: String,
    val userId : String,
    val date: FullDate,
    val time: TimeSocket,
    val vaccineId : String,
 ){
    constructor() : this(
        "",
        "",
        "",
        FullDate(),
        TimeSocket(),
        vaccineId = ""
    )
}


