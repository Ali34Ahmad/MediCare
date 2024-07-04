package com.example.medicare.data.childTable

import com.example.medicare.data.model.child.VaccineTableItem

interface ChildTable {
    /**Initialize the table with the default rows*/
    suspend fun initTable(childId : String)
    /**Add a new vaccine to the default table*/
    suspend fun addToTable(vaccine : VaccineTableItem)
    /**Delete a vaccine from the default table*/
    suspend fun deleteFromTable(vaccineId : String)
}