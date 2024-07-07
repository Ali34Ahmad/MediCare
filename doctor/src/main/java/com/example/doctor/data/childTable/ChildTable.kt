package com.example.doctor.data.childTable

import com.example.doctor.data.model.child.VaccineTableItem


interface ChildTable {
    /**Initialize the table with the default rows*/
    suspend fun initTable(childId : String)
    /**Add a new vaccine to the default table*/
    suspend fun addToDefaultTable(vaccine : VaccineTableItem)
    /**Delete a vaccine from the default table*/
    suspend fun deleteFromDefaultTable(vaccineId : String)
}