package com.example.medicare.data.services

import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.user.Doctor
import com.example.medicare.data.model.user.User
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.appointment.Appointment
import kotlinx.coroutines.flow.Flow


interface StorageService {
    /**
     * Add a new user to the database with the same ID as the current user
     */
    suspend fun addNewUser(user: User)
    /**
     * Get a user from the database by its ID
     */
    suspend fun getUser() : User?

    /**
     * Add a new child to the user's children collection
     */
    suspend fun addChild(child : Child)

    /**
     * Get all user's children
     */
    val children : Flow<List<Child>>

    /**
     * Add new vaccineTableItem to child's vaccines collection
     */
    suspend fun addVaccineTableItem(vaccineTableItem: VaccineTableItem, childId: String)

    /**
     * Get vaccine table for a child by its Id
     */
    suspend fun getVaccineTable(childId: String) :List<VaccineTableItem>

    /**
     * Add new Vaccine
     */
    suspend fun addVaccine(vaccine: Vaccine)

    /**
     * Get all the vaccines from database.
     */
    val vaccines  : Flow<List<Vaccine>>

    /**
     * Add new Doctor
     */
    suspend fun addDoctor(doctor: Doctor)

    /**
     * Create new clinic
     */
    suspend fun createNewClinic(clinic: Clinic)
    /**
     * get the current clinics
     */
    val clinics : Flow<List<Clinic>>
    suspend fun addClinic(clinic: Clinic)
    /**
     * Add a new appointment in the clinic -> appointment collection
     * and the function returns the id of the appointment
     */
    suspend fun addAppointment(appointment: Appointment)
    /**
     * Get all the user appointments
     */
    val appointments : Flow<List<Appointment>>

    /**
     * Upload a photo to the cloud storage and return the url of it
     * to download it later.
     */
}