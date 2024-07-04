package com.example.doctor.data.repositories

import com.example.doctor.data.model.user.Doctor

interface DoctorRepository {
    /**Add doctor to the database*/
    suspend fun addDoctor(doctor: Doctor)

}