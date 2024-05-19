package com.example.medicare.data.model.date

import com.example.medicare.data.model.enums.AgeUnit

data class Age (
    val age: Int,
    val unit : AgeUnit
){
    constructor() : this(0, AgeUnit.YEARS)
    val fullAge: String = "$age $unit"
}