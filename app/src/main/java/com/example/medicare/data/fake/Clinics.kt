package com.example.medicare.data.fake

import com.example.medicare.core.enums.Gender
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.user.Doctor


val doctor1= Doctor(
    firstName = "Ali",
    lastName = "Ahmad",
    speciality = "Ophthalmologist",
    imageUrl = "",
    gender = Gender.MALE
)
val clinic1=
    Clinic(
        name = "Eye",
        imageUrl = "",
        workDays = listOfWorkDays,
        daySockets = listOfDaySockets,
        responsibleDoctor = doctor1,
        services = emptyList()
    )
val vaccinesClinic=
    Clinic(
        name = "Vaccines",
        imageUrl = "",
        workDays = listOfWorkDays,
        daySockets = listOfDaySockets,
        responsibleDoctor = doctor1,
        services = emptyList()
    )
