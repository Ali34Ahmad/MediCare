package com.example.medicare.data.model.user

import com.example.medicare.core.enums.Gender
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

data class Doctor(
    val id : String ="",
    val firstName : String="",
    val lastName : String="",
    val speciality : String = "",
    val imageUrl : String? = "",
    val gender: Gender = Gender.MALE
){
    val fullName : String = "$firstName $lastName"
    private constructor() : this(
        id = "",
        firstName = "",
        lastName = "",
        speciality = "",
        imageUrl = null,
        gender = Gender.MALE
    )
}