package com.example.doctor.data.model.date

import com.example.doctor.core.enums.DayOfWeek
import com.example.doctor.data.model.date.Time

data class WorkDay (
    val day : DayOfWeek,
    val openingTime : Time,
    val closingTime : Time
){
    private constructor() : this(day= DayOfWeek.SUN,openingTime= Time(),closingTime= Time())
}