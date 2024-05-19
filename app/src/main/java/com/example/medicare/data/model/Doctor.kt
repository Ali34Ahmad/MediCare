package com.example.medicare.data.model

import com.example.medicare.data.model.enums.Gender
import com.google.firebase.firestore.DocumentId

data class Doctor(
    @DocumentId
    val id : String ="",
    val firstName : String,
    val lastName : String,
    val speciality : String = "",
    val img : String? = "",
    val gender: Gender = Gender.MALE
){
    val fullName : String = "$firstName $lastName"
    constructor() : this(firstName = "", lastName = "")
}