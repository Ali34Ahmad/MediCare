package com.example.medicare.data.model.date

import com.example.medicare.core.enums.DayOfWeek
import com.google.firebase.firestore.DocumentId


data class WorkDay (
    val day : DayOfWeek,
    val openingTime : Time,
    val closingTime : Time
){
    constructor() : this(day=DayOfWeek.SUN,openingTime=Time(),closingTime=Time())
}