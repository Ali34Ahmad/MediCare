package com.example.medicare.core

import com.example.medicare.data.model.Child
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.enums.AgeUnit
import com.example.medicare.data.model.enums.DayOfWeek
import com.example.medicare.data.model.enums.Month

fun Int.toDay() : DayOfWeek{
    return DayOfWeek.entries[this]
}
fun Int.toMonth() : Month{
    return Month.entries[this]
}

fun FullDate.toAge() : Age {
    val currentDate = FullDate.getCurrentDate()
    val yearDiff = this.year - currentDate.year
    if(yearDiff > 0 ){
        return Age(yearDiff,AgeUnit.YEARS)
    }
    val monthDiff = this.month.ordinal - currentDate.month.ordinal
    if(monthDiff > 0 ){
        return Age(monthDiff,AgeUnit.MONTHS)
    }
    val dayDiff = this.day.ordinal - currentDate.day.ordinal
    return Age(dayDiff,AgeUnit.DAYS)
}

fun Child.getAge() : Age = this.birthDate.toAge()
