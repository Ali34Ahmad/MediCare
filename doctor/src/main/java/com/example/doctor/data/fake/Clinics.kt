package com.example.doctor.data.fake

import com.example.doctor.core.enums.Gender
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.user.Doctor
val doctor1=Doctor(
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
