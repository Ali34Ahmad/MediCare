package com.example.doctor.data.model.date

import com.example.doctor.core.enums.TimeUnit

data class RemainingTime (
    val remainingTime:Int,
    val timeUnit: TimeUnit
)