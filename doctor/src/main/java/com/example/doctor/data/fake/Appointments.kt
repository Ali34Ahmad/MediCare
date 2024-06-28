package com.example.doctor.data.fake

import com.example.doctor.core.enums.DayPeriod
import com.example.doctor.core.enums.Month
import com.example.doctor.core.enums.TimeSocketState
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.date.Time
import com.example.doctor.data.model.date.TimeSocket

val appointment1 = Appointment(
    clinicId = "23d",
    userId = "Ali",
    date = FullDate(10, Month.JUN, 2023),
    timeSocket = TimeSocket(
        Time(10, 10, DayPeriod.AM),
        state = TimeSocketState.FREE
    )
)
val listOfAppointments= listOf(
    appointment1,
    appointment1,
    appointment1,
    appointment1,
)