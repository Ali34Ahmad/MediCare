package com.example.medicare.data.model.date

data class DaySocket (
    val date: FullDate,
    val timeSockets: List<TimeSocket> = emptyList()
)