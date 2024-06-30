package com.example.doctor.data.model.child

import kotlinx.serialization.Serializable

data class ChildNumber(
    val firstNumber : Int = 0,
    val secondNumber : Int = 0
){
    private constructor() : this(0,0)
}