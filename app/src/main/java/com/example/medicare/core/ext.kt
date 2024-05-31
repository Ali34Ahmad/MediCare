package com.example.medicare.core

import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.core.enums.AgeUnit
import com.example.medicare.core.enums.Month


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

fun Child.getAge() : Age = this.birthDate.toAge()
