package com.example.medicare.data.model.date

import java.time.DayOfWeek


data class WorkDay (
    val day : String,
    val openingTime : Time,
    val closingTime : Time
){
    constructor() : this(
        day = "Monday",
        openingTime = Time(),
        closingTime = Time(),
    )
}
