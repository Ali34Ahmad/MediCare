package com.example.medicare.data.model

import com.example.medicare.data.model.enums.Gender

data class User(
    val email : String ,
    val firstName : String ,
    val lastName : String ,
    val gender : Gender,
    val children : List<Child> = emptyList()
)