package com.example.doctor.data.repositories.impl

import com.example.doctor.core.constants.DatabaseCollections
import com.example.doctor.data.model.user.Doctor
import com.example.doctor.data.repositories.DoctorRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    database: FirebaseFirestore,
    auth: FirebaseAuth
): DoctorRepository {

    private val currentUserId = auth.currentUser?.uid ?: ""

    private val doctorRef = database.collection(DatabaseCollections.DOCTORS_COLLECTION)

    override suspend fun addDoctor(doctor: Doctor) {
        val currentDoctor = doctor.copy(id=currentUserId)
        doctorRef.document(currentUserId).set(currentDoctor).await()
    }
}
