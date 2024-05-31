package com.example.medicare.data.model.date

import com.google.firebase.firestore.DocumentId

data class WorkDay (
    val day : Int,
    val openingTime : Time,
    val closingTime : Time
)