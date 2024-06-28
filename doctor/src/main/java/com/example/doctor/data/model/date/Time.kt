package com.example.doctor.data.model.date

import com.example.doctor.core.enums.DayPeriod

data class Time(
    val hour: Int = 0,
    val minute: Int = 0,
    val dayPeriod: DayPeriod = DayPeriod.AM
){
    private constructor() : this(0,0, DayPeriod.AM)
}