package com.example.medicare.data.model

import com.example.medicare.core.toAge
import com.example.medicare.data.model.date.Age
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.enums.Gender

data class Child(
    val firstName : String,
    val lastName : String,
    val birthDate : FullDate,
    val gender : Gender,
    //the unique id of the child
    val childNumber : ChildNumber,
    val vaccineTable : List<VaccineTableItem>
){
        fun getAge() : Age = this.birthDate.toAge()
}
