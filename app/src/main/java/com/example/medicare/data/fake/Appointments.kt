package com.example.medicare.data.fake

import com.example.medicare.core.enums.DayPeriod
import com.example.medicare.core.enums.Month
import com.example.medicare.core.enums.TimeSocketState
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.date.Time
import com.example.medicare.data.model.date.TimeSocket

val appointment1 = Appointment(
    clinicId = "23d",
    userId = "Ali",
    date = FullDate(10, Month.JUN, 2023),
    timeSocket = TimeSocket(
        Time(10, 10, DayPeriod.AM),
        state = TimeSocketState.FREE
    )
)
val appointment2 = Appointment(
    clinicId = "23d",
    userId = "Ali",
    date = FullDate(10, Month.JUN, 2023),
    timeSocket = TimeSocket(
        Time(10, 10, DayPeriod.AM),
        state = TimeSocketState.FREE
    ),
    vaccineId = mmrVaccine.id
)

val listOfAppointments= listOf(
    appointment1,
    appointment1,
    appointment1,
    appointment1,
)