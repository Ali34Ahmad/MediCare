package com.example.medicare.data.model.date

import com.example.medicare.core.enums.DayPeriod
import kotlinx.serialization.Serializable

data class Time(
    val hour: Int = 0,
    val minute: Int = 0,
    val dayPeriod: DayPeriod = DayPeriod.AM
){
    private constructor() : this(0,0, DayPeriod.AM)
}