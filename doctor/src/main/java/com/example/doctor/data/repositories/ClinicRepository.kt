package com.example.doctor.data.repositories

import com.example.doctor.data.model.clinic.Clinic
import kotlinx.coroutines.flow.Flow

interface ClinicRepository {
    /** get the current clinics*/
    val clinics : Flow<List<Clinic>>
    /** add a new clinic*/
    suspend fun addClinic(clinic: Clinic)

    suspend fun getClinicById(id: String) : Clinic?

    suspend fun deleteClinic(id: String)

    suspend fun updateClinic(clinic: Clinic)

}