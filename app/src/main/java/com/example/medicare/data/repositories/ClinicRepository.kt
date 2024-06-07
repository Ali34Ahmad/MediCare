package com.example.medicare.data.repositories

import com.example.medicare.data.model.clinic.Clinic
import kotlinx.coroutines.flow.Flow

interface ClinicRepository {
    /** get the current clinics*/
    val clinics : Flow<List<Clinic>>
    /** add a new clinic*/
    suspend fun addClinic(clinic: Clinic)
}