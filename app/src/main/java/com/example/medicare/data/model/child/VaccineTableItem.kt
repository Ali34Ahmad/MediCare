package com.example.medicare.data.model.child

import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.vaccine.Vaccine
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

data class VaccineTableItem(
    @DocumentId
    val id : String="",
    val vaccine: Vaccine=Vaccine(),
    val vaccineDate : FullDate?
){
    private constructor() : this(
        id = "",
        vaccine = Vaccine(),
        vaccineDate = FullDate()
    )
}