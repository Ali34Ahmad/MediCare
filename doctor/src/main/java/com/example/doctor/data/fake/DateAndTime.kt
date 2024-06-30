package com.example.doctor.data.fake

import com.example.doctor.core.enums.DayOfWeek
import com.example.doctor.core.enums.DayPeriod
import com.example.doctor.core.enums.Month
import com.example.doctor.core.enums.TimeSocketState
import com.example.doctor.data.model.date.DaySocket
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.date.Time
import com.example.doctor.data.model.date.TimeSocket
import com.example.doctor.data.model.date.WorkDay


val listOfWorkDays = listOf(
    WorkDay(DayOfWeek.SUN, Time(9, 0, DayPeriod.AM), Time(3, 0, DayPeriod.PM)),
    WorkDay(DayOfWeek.MON, Time(9, 0, DayPeriod.AM), Time(3, 0, DayPeriod.PM)),
    WorkDay(DayOfWeek.TUE, Time(9, 0, DayPeriod.AM), Time(3, 0, DayPeriod.PM)),
    WorkDay(DayOfWeek.WED, Time(9, 0, DayPeriod.AM), Time(3, 0, DayPeriod.PM)),
    WorkDay(DayOfWeek.THU, Time(9, 0, DayPeriod.AM), Time(3, 0, DayPeriod.PM)),
    WorkDay(DayOfWeek.SAT, Time(9, 0, DayPeriod.AM), Time(3, 0, DayPeriod.PM)),
)
val listOfTimeSockets =
    listOf(
        TimeSocket(Time(9, 0, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(9, 15, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(9, 30, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(9, 45, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(10, 0, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(10, 15, DayPeriod.AM), TimeSocketState.FREE),

        TimeSocket(Time(10, 30, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(10, 45, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(11, 0, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(11, 15, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(11, 30, DayPeriod.AM), TimeSocketState.FREE),
        TimeSocket(Time(11, 45, DayPeriod.AM), TimeSocketState.FREE),

        TimeSocket(Time(12, 0, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(12, 15, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(12, 30, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(12, 45, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(1, 0, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(1, 15, DayPeriod.PM), TimeSocketState.FREE),

        TimeSocket(Time(1, 30, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(1, 45, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(2, 0, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(2, 15, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(2, 30, DayPeriod.PM), TimeSocketState.FREE),
        TimeSocket(Time(2, 45, DayPeriod.PM), TimeSocketState.FREE),
    )
val listOfDaySockets = listOf(
    DaySocket(FullDate(10, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(11, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(12, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(13, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(14, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(15, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(16, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(17, Month.JUL, 2024), timeSockets = listOfTimeSockets),
    DaySocket(FullDate(18, Month.JUL, 2024), timeSockets = listOfTimeSockets),
)