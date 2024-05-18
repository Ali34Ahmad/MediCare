package com.example.medicare.data.services

import com.example.medicare.data.model.Doctor
import com.example.medicare.data.model.User
import kotlinx.coroutines.flow.Flow


interface StorageService {
    suspend fun addNewUser(user: User)
    suspend fun getUser(userId : String) : User?
    val doctors : Flow<Doctor>
}