package com.example.medicare.data.repositories

import com.example.medicare.data.model.vaccine.Vaccine
import kotlinx.coroutines.flow.Flow

interface VaccineRepository {
    /**Get all the vaccines from database.*/
    val vaccines  : Flow<List<Vaccine>>
    /**Add new Vaccine*/
    suspend fun addVaccine(vaccine: Vaccine)
}
