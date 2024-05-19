package com.example.medicare.data.model.date

import com.example.medicare.data.model.enums.DayPeriod

data class Time(
    val hour: Int,
    val minute: Int,
    val dayPeriod: DayPeriod
){
    constructor() : this(0,0,DayPeriod.AM)
}