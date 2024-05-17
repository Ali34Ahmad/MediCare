package com.example.medicare.data.model.appointment

import com.example.medicare.data.model.date.FullDate

data class ClinicAppointment(
    override val id: String,
    override val userId: String,
    override val firstName: String,
    override val lastName: String,
    override val email: String,
    override val date: FullDate,
    override val time: String,
    val doctorId : String
): Appointment()