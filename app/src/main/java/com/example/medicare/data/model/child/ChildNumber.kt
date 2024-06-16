package com.example.medicare.data.model.child

data class ChildNumber(
    val firstNumber : Int = 0,
    val secondNumber : Int = 0
){
    private constructor() : this(0,0)
}