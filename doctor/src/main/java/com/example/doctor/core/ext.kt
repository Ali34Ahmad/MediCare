package com.example.doctor.core

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.navigation.NavController
import com.example.doctor.presentation.MedicareAppViewModel
import com.example.doctor.data.model.child.Child
import com.example.doctor.data.model.date.Age
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.core.enums.AgeUnit
import com.example.doctor.core.enums.DayOfWeek
import com.example.doctor.core.enums.DayPeriod
import com.example.doctor.core.enums.Month
import com.example.doctor.core.enums.TimeUnit
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.date.RemainingTime
import com.example.doctor.data.model.date.Time
import com.example.doctor.data.model.date.TimeSocket
import com.example.doctor.data.model.date.WorkDay
import com.example.doctor.presentation.navigation.Destination
import com.example.doctor.presentation.navigation.getCurrentDestinationUsingName
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.DurationUnit


fun Int.toMonth() : Month {
    val index = this - 1
    return Month.entries[index]
}

fun FullDate.toAge() : Age {
    val currentDate = FullDate.getCurrentDate()
    val yearDiff =currentDate.year - this.year
    if(yearDiff > 0 ){
        return Age(yearDiff, AgeUnit.YEARS)
    }
    val monthDiff =currentDate.month.ordinal - this.month.ordinal
    if(monthDiff > 0 ){
        return Age(monthDiff, AgeUnit.MONTHS)
    }
    val dayDiff = currentDate.day - this.day
    return Age(dayDiff, AgeUnit.DAYS)
}

fun FullDate.getDayOfWeek(): DayOfWeek {
    val localDate = LocalDate.of(year, month.ordinal, day)
    val dayOfWeek = localDate.dayOfWeek

    return dayOfWeek.toString().getDayOfWeek()
}
fun String.convertToProperCase(): String {
    val lowercase = this.lowercase()
    return lowercase.replaceFirstChar { it.uppercase() }
}

fun Child.getAge() : Age = this.birthDate.toAge()

fun Clinic.isOpenNow() : Boolean {
    val today = LocalDate.now()
    val dayName = today.dayOfWeek.name
    val workDayIndex = workDays.indexOfFirst { it.day == dayName.getDayOfWeek() }
    if (workDayIndex==-1) return false
    val currentTime = LocalTime.now()
    return !(currentTime < this.workDays[workDayIndex].openingTime.toLocalTime() ||
            currentTime > this.workDays[workDayIndex].closingTime.toLocalTime())
}
fun String.getDayOfWeek(): DayOfWeek {
    val dayName = this.uppercase()

    return DayOfWeek.values().find { dayName.contains(it.name) }
        ?: DayOfWeek.SUN
}
fun Time.toLocalTime():LocalTime{
    val hours=if(dayPeriod== DayPeriod.AM) hour else (hour+12)%24
    return LocalTime.of(hours,minute)
}

fun Appointment.calculateRemainingTime(): RemainingTime {
    val targetDate: FullDate=this.date
    val targetTime: TimeSocket=this.timeSocket

    val currentDateTime= LocalDateTime.now()
    val targetDateTime = LocalDateTime.of(targetDate.year, targetDate.month.ordinal, targetDate.day, targetTime.time.hour, targetTime.time.minute)

    val duration = currentDateTime.until(targetDateTime, ChronoUnit.MINUTES)
    val MINUTES_IN_DAY=1440
    val MINUTES_IN_HOUR=60

    val remainingDays = duration.days/MINUTES_IN_DAY
    val remainingHours = duration.hours/MINUTES_IN_HOUR
    val remainingMinutes = duration.minutes

    // Determine the largest remaining time unit
    val timeUnit = when {
        remainingDays.toInt(DurationUnit.DAYS) > 0 -> TimeUnit.DAY
        remainingHours.toInt(DurationUnit.HOURS) > 0 -> TimeUnit.HOUR
        else -> TimeUnit.MINUTE
    }
    val durationUnit: DurationUnit

    val remainingTime = when (timeUnit) {
        TimeUnit.DAY ->{
            durationUnit= DurationUnit.DAYS
            remainingDays
        }
        TimeUnit.HOUR -> {
            durationUnit= DurationUnit.HOURS
            remainingHours
        }
        TimeUnit.MINUTE -> {
            durationUnit= DurationUnit.MINUTES
            remainingMinutes
        }
    }

    return RemainingTime(remainingTime.toInt(durationUnit), timeUnit)
}

fun FullDate.formatDate():String{
    val day= if(this.day.toString().length==1)
        "0${this.day}"
    else
        "${this.day}"

    val month= if((this.month.ordinal+1).toString().length==1)
        "0${this.month.ordinal+1}"
    else
        "${this.month.ordinal+1}"

    val year= this.year

    return  "$day/$month/$year"
}
fun Time.formatTime():String{
    val hours= if(this.hour.toString().length==1)
        "0${this.hour}:"
    else
        "${this.hour}:"

    val minutes= if(this.minute.toString().length==1)
        "0${this.minute}"
    else
        "${this.minute}"

    return  "$hours:$minutes ${dayPeriod.name}"
}

fun java.time.Month.getMonthByJavaMonth(): Month {
    val values = Month.entries.toTypedArray()
    return if (this.ordinal in values.indices) {
        values[this.ordinal]
    }else
        values[0]
}



fun <T : Any> NavController.navigate(destination: T, viewModel: MedicareAppViewModel) {
    navigate(destination)
    if (destination is Destination)
        viewModel.updateCurrentDestination(destination)
}

inline fun <reified T : Any> NavController.popUpToAndNavigate(
    destination: Destination,
    viewModel: MedicareAppViewModel
) {
    navigate(destination) {
        popUpTo<T> {
            inclusive = true
        }
    }
    viewModel.updateCurrentDestination(destination)
}


fun NavController.navigateUp(viewModel: MedicareAppViewModel) {
    navigateUp()
    viewModel.updateCurrentDestination(getCurrentDestinationUsingName(this))
}

fun DayOfWeek.isNotWorkDay(workDays:List<WorkDay>):Boolean{
    val workDayIndex = workDays.indexOfFirst { it.day == this }
    return workDayIndex==-1
}
fun FullDate.toLocalDate():LocalDate{
    return LocalDate.of(year,month.ordinal,day)
}
fun LocalDate.toFullDate():FullDate{
    return FullDate(dayOfMonth,month.getMonthByJavaMonth(),year)
}
fun Appointment.isUpcoming(): Boolean {
    return date.toLocalDate() >= LocalDate.now()&&timeSocket.time.toLocalTime() > LocalTime.now()
}
