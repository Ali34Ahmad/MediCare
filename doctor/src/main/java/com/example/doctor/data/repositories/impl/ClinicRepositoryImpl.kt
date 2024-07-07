package com.example.doctor.data.repositories.impl

import com.example.doctor.core.constants.DatabaseCollections
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.user.Doctor
import com.example.doctor.data.repositories.ClinicRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ClinicRepositoryImpl @Inject constructor(
    database: FirebaseFirestore,
    auth: FirebaseAuth
) : ClinicRepository {

    private val clinicRef = database.collection(DatabaseCollections.CLINICS_COLLECTION)
    private val currentUserId = auth.currentUser?.uid ?: "0"
    override val clinics: Flow<List<Clinic>>
        get() = clinicRef.snapshots().map { snapshot ->
            snapshot.toObjects(Clinic::class.java)
        }

    override suspend fun addClinic(clinic: Clinic) {
        val currentClinic = clinic.copy(
            responsibleDoctor = clinic.responsibleDoctor.copy(id = currentUserId)
        )
        clinicRef.add(currentClinic).await()
    }

    override suspend fun getClinicById(id: String): Clinic? {
        return clinicRef.document(id).get().await().toObject(Clinic::class.java)
    }

    override suspend fun deleteClinic(id: String) {
        clinicRef.document(id).delete().await()
    }

    override suspend fun updateClinic(clinic: Clinic) {
        clinicRef.document(clinic.id).set(clinic).await()
    }

    override suspend fun getResponsibleDoctor(clinicId: String): Doctor? {
        var doctor: Doctor? = null
        clinicRef.document(clinicId).get().await().toObject(Clinic::class.java)?.let {
            doctor = it.responsibleDoctor
        }
        return doctor
    }

    override suspend fun getClinicIdByDoctor(doctorId: String): String? =
         clinicRef.whereEqualTo("responsibleDoctor.id", doctorId).get()
            .await().toObjects(Clinic::class.java).firstOrNull()?.id
}