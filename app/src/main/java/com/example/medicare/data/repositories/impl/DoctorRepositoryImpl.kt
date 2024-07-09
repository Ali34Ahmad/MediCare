package com.example.medicare.data.repositories.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.user.Doctor
import com.example.medicare.data.repositories.DoctorRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    database: FirebaseFirestore,
    private val auth: FirebaseAuth
): DoctorRepository {

    private val currentUserId :String?
        get() = auth.currentUser?.uid

    private val doctorRef = database.collection(DatabaseCollections.DOCTORS_COLLECTION)

    override suspend fun addDoctor(doctor: Doctor) {
        currentUserId?.let {
            val currentDoctor = doctor.copy(id = currentUserId!!)
            doctorRef.document(currentUserId!!).set(currentDoctor).await()
        }
    }
}
