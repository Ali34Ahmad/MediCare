package com.example.medicare.data.model.user

import com.example.medicare.core.enums.Gender
data class User(
    val email : String,
    val firstName : String,
    val lastName : String,
    val gender : Gender,
){
    private
    constructor() : this(
        email = "",
        firstName = "no name",
        lastName= "no name",
        gender = Gender.NOT_SPECIFIED
    )
}