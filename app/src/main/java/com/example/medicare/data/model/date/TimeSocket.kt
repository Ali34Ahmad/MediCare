package com.example.medicare.data.model.date

import com.example.medicare.data.model.enums.TimeSocketState
import com.google.firebase.firestore.DocumentId

data class TimeSocket (
    val time: Time,
    val state : TimeSocketState
){
    constructor() : this(time = Time(),state = TimeSocketState.FREE)
}