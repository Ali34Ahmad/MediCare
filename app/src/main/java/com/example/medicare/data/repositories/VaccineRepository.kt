package com.example.medicare.data.repositories

import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.model.vaccine.Vaccine
import kotlinx.coroutines.flow.Flow

interface VaccineRepository {
    /**Get all the vaccines from database.*/
    val vaccines  : Flow<List<Vaccine>>
    /**Add new Vaccine*/
    suspend fun addVaccine(vaccine: Vaccine)
    /**Get the default vaccines table that given for all children*/
    val defaultVaccines : Flow<List<VaccineTableItem>>
}
