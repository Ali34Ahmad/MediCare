package com.example.medicare.data.repositories

import com.example.medicare.data.model.user.Doctor

interface DoctorRepository {
    /**Add doctor to the database*/
    suspend fun addDoctor(doctor: Doctor)
}