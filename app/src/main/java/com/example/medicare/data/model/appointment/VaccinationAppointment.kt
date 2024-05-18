package com.example.medicare.data.model.appointment

import com.example.medicare.data.model.date.FullDate

data class VaccinationAppointment(
    override val id: String,
    override val userId: String,
    override val date: FullDate,
    override val time: String,
    override val firstName: String,
    override val lastName: String,
    override val email: String,
    val vaccineId : String,
) : Appointment()
