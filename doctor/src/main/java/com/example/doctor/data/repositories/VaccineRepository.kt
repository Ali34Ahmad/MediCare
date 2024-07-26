package com.example.doctor.data.repositories

import com.example.doctor.data.model.child.VaccineTableItem
import com.example.doctor.data.model.vaccine.Vaccine
import kotlinx.coroutines.flow.Flow

interface VaccineRepository {
    /**Get all the vaccines from database.*/
    val vaccines  : Flow<List<Vaccine>>
    /**Get the default vaccines table that given for all children*/
    val defaultVaccines : Flow<List<VaccineTableItem>>
    /**Add new Vaccine*/
    suspend fun addVaccine(vaccine: Vaccine)
}
