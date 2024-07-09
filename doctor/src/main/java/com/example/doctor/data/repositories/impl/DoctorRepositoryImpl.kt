package com.example.doctor.data.repositories.impl

import com.example.doctor.core.constants.DatabaseCollections
import com.example.doctor.data.model.user.Doctor
import com.example.doctor.data.repositories.DoctorRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore,
    private val auth: FirebaseAuth
): DoctorRepository {

    private val currentUserId :String? get()= auth.currentUser?.uid

    private val doctorRef = database.collection(DatabaseCollections.DOCTORS_COLLECTION)

    override suspend fun addDoctor(doctor: Doctor) {
        val currentDoctor = currentUserId?.let { doctor.copy(id= it) }
        currentUserId?.let {
            if (currentDoctor != null) {
                doctorRef.document(it).set(currentDoctor).await()
            }
        }
    }
}
