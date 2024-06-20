package com.example.medicare.data.repositories

import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.date.FullDate
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
    /**get the number of appointments for a user*/
    suspend fun getNumberOfAppointments(userId: String): Int
    /**get appointments by data*/
    suspend fun getAppointmentsByDate(date : FullDate): Flow<List<Appointment>>
}