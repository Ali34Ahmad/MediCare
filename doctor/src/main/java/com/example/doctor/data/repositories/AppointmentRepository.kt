package com.example.doctor.data.repositories

import com.example.doctor.data.model.appointment.Appointment
import kotlinx.coroutines.flow.Flow

interface AppointmentRepository {
    /**
     * Add a new appointment in the clinic -> appointment collection
     * and the function returns the id of the appointment
     */
    suspend fun addAppointment(appointment: Appointment)
    /**Get all the user appointments*/
    val appointments : Flow<List<Appointment>>
    /**Delete the appointment with the given id*/
    suspend fun deleteAppointment(id: String)
}