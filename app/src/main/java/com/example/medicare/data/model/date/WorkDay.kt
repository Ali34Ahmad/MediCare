package com.example.medicare.data.model.date

import com.example.medicare.data.model.enums.DayOfWeek
import com.google.firebase.firestore.DocumentId

data class WorkDay (
    val day : DayOfWeek,
    val openingTime : Time,
    val closingTime : Time
)