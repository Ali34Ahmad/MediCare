package com.example.medicare.data.model.child

import com.example.medicare.data.model.date.FullDate
import com.example.medicare.core.enums.Gender
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable


data class Child(
    @DocumentId
    val id : String = "",
    val firstName : String="",
    val lastName : String="",
    val father : String="",
    val mother : String="",
    val birthDate : FullDate= FullDate(),
    val gender : Gender=Gender.MALE,
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