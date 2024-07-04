package com.example.medicare.core


import androidx.navigation.NavController
import com.example.medicare.core.enums.AgeUnit
import com.example.medicare.core.enums.DayOfWeek
import com.example.medicare.core.enums.DayPeriod
import com.example.medicare.core.enums.Month
import com.example.medicare.core.enums.TimeUnit
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.date.RemainingTime
import com.example.medicare.data.model.date.Time
import com.example.medicare.data.model.date.TimeSocket
import com.example.medicare.data.model.date.WorkDay
import com.example.medicare.presentation.MedicareAppViewModel
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.navigation.getCurrentDestinationUsingName
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
    val localDate = java.time.LocalDate.of(year, month.ordinal, day)
    val dayOfWeek = localDate.dayOfWeek

    return dayOfWeek.toString().getDayOfWeek()
}
fun String.convertToProperCase(): String {
    val lowercase = this.lowercase()
    return lowercase.replaceFirstChar { it.uppercase() }
}

fun Child.getAge() : Age = this.birthDate.toAge()

fun Clinic.isOpenNow() : Boolean {
    val today = java.time.LocalDate.now()
    val dayName = today.dayOfWeek.name
    val workDayIndex = workDays.indexOfFirst { it.day == dayName.getDayOfWeek() }
    if (workDayIndex==-1) return false
    val currentTime = java.time.LocalTime.now()
    return !(currentTime < this.workDays[workDayIndex].openingTime.toLocalTime() ||
            currentTime > this.workDays[workDayIndex].closingTime.toLocalTime())
}
fun String.getDayOfWeek(): DayOfWeek {
    val dayName = this.uppercase()
    return when (dayName) {
        "SUNDAY" -> DayOfWeek.SUN
        "MONDAY" -> DayOfWeek.MON
        "TUESDAY" -> DayOfWeek.TUE
        "WEDNESDAY" -> DayOfWeek.WED
        "THURSDAY" -> DayOfWeek.THU
        "FRIDAY" -> DayOfWeek.FRI
        "SATURDAY" -> DayOfWeek.SAT
        else -> DayOfWeek.SUN
    }
}
fun Time.toLocalTime():LocalTime{
    val hours=if(dayPeriod== DayPeriod.AM) hour else (hour+12)%24
    return java.time.LocalTime.of(hours,minute)
}

fun Appointment.calculateRemainingTime(targetDate: FullDate, targetTime: TimeSocket): RemainingTime {
    val currentDateTime= LocalDateTime.now()
    val targetDateTime = LocalDateTime.of(targetDate.year, targetDate.month.ordinal, targetDate.day.toInt(), targetTime.time.hour, targetTime.time.minute)

    val duration = currentDateTime.until(targetDateTime, java.time.temporal.ChronoUnit.MINUTES)
    val MINUTES_IN_DAY=1440
    val MINUTES_IN_HOUR=1440

    val remainingDays = duration.days/MINUTES_IN_DAY
    val remainingHours = duration.hours/MINUTES_IN_HOUR
    val remainingMinutes = duration.minutes

    // Determine the largest remaining time unit
    val timeUnit = when {
        remainingDays.toInt(kotlin.time.DurationUnit.DAYS) > 0 -> TimeUnit.DAY
        remainingHours.toInt(kotlin.time.DurationUnit.HOURS) > 0 -> TimeUnit.HOUR
        else -> TimeUnit.MINUTE
    }
    val durationUnit: DurationUnit

    val remainingTime = when (timeUnit) {
        TimeUnit.DAY ->{
            durationUnit= kotlin.time.DurationUnit.DAYS
            remainingDays
        }
        TimeUnit.HOUR -> {
            durationUnit= kotlin.time.DurationUnit.HOURS
            remainingHours
        }
        TimeUnit.MINUTE -> {
            durationUnit= kotlin.time.DurationUnit.MINUTES
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
    return java.time.LocalDate.of(year,month.ordinal,day)
}
fun LocalDate.toFullDate():FullDate{
    return FullDate(dayOfMonth,month.getMonthByJavaMonth(),year)
}
