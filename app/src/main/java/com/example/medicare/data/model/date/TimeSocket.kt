package com.example.medicare.data.model.date

import com.example.medicare.data.model.enums.TimeSocketState

data class TimeSocket (
    val time: Time,
    val state : TimeSocketState = TimeSocketState.FREE
)