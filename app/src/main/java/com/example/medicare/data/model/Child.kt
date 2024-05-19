package com.example.medicare.data.model

import com.example.medicare.core.toAge
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.enums.Gender
import com.google.firebase.firestore.DocumentId

data class Child(
    @DocumentId
    val id : String = "",
    val firstName : String,
    val lastName : String,
    val birthDate : FullDate,
    val gender : Gender,
    val childNumber : ChildNumber,
    val vaccineTable : List<VaccineTableItem>
)