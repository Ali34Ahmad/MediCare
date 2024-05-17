package com.example.medicare.data.model.section

import com.example.medicare.data.model.date.DaySocket
import com.example.medicare.data.model.date.WorkDay

abstract class Section{
    abstract val id :String
    abstract val name : String
    abstract val workDays : List<WorkDay>
    abstract val daySockets : List<DaySocket>
}