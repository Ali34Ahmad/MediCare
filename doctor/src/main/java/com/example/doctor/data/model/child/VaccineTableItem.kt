package com.example.doctor.data.model.child

import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.vaccine.Vaccine
import com.google.firebase.firestore.DocumentId

data class VaccineTableItem(
    @DocumentId
    val id : String,
    val vaccine: Vaccine,
    val vaccineDate : FullDate?
){
    private constructor() : this(
        id = "",
        vaccine = Vaccine(),
        vaccineDate = FullDate()
    )
}