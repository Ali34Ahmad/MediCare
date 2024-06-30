package com.example.doctor.data.model.date

import com.example.doctor.core.enums.TimeSocketState
import com.example.doctor.data.model.date.Time

data class TimeSocket (
    val time: Time,
    val state : TimeSocketState
){
    constructor() : this(time = Time(),state = TimeSocketState.FREE)
}