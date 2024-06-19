package com.example.medicare.data.model.date

import com.example.medicare.core.enums.DayOfWeek

data class WorkDay (
    val day : DayOfWeek,
    val openingTime : Time,
    val closingTime : Time
){
    private constructor() : this(day=DayOfWeek.SUN,openingTime=Time(),closingTime=Time())
}