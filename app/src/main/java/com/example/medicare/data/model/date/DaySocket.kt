package com.example.medicare.data.model.date

import com.example.medicare.core.enums.Month
import com.google.firebase.firestore.DocumentId
import kotlinx.serialization.Serializable

data class DaySocket (
    val date: FullDate,
    val timeSockets: List<TimeSocket> = emptyList()
){
    constructor() : this(date= FullDate(1,Month.JUN,2024),timeSockets= emptyList())
}