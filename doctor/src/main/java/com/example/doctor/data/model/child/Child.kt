package com.example.doctor.data.model.child

import com.example.doctor.data.model.date.FullDate
import com.example.doctor.core.enums.Gender
import com.google.firebase.firestore.DocumentId


data class Child(
    @DocumentId
    val id : String = "",
    val firstName : String="",
    val lastName : String="",
    val father : String="",
    val mother : String="",
    val birthDate : FullDate = FullDate(),
    val gender : Gender = Gender.MALE,
    val childNumber : ChildNumber = ChildNumber(),
    val vaccineTable : List<VaccineTableItem> = emptyList()
){
    private constructor() : this(
        firstName = "no name",
        lastName = "no name",
        father = "no name",
        mother= "no name",
        birthDate = FullDate(),
        gender = Gender.MALE,
        childNumber = ChildNumber(),
        vaccineTable = emptyList()
    )
}