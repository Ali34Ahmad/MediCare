package com.example.medicare.data.model.date

import com.google.firebase.firestore.DocumentId

data class DaySocket (
    val date: FullDate,
    val timeSockets: List<TimeSocket> = emptyList()
){
}