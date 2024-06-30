package com.example.doctor.data.model.date

import com.example.doctor.core.enums.Month

data class DaySocket (
    val date: FullDate,
    val timeSockets: List<TimeSocket> = emptyList()
){
    constructor() : this(date= FullDate(1, Month.JUN,2024),timeSockets= emptyList())
}