package com.example.doctor.data.repositories.impl

import com.example.doctor.core.constants.DatabaseCollections
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.repositories.ClinicRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ClinicRepositoryImpl @Inject constructor(
    database: FirebaseFirestore,
) : ClinicRepository {

    private val clinicRef = database.collection(DatabaseCollections.CLINICS_COLLECTION)

    override val clinics: Flow<List<Clinic>>
        get() = clinicRef.snapshots().map {snapshot ->
            snapshot.toObjects(Clinic::class.java)
        }

    override suspend fun addClinic(clinic: Clinic) {
        clinicRef.add(clinic).await()
    }

    override suspend fun getClinicById(id: String): Clinic?  {
       return clinicRef.document(id).get().await().toObject(Clinic::class.java)
    }

    override suspend fun deleteClinic(id: String) {
         clinicRef.document(id).delete().await()
    }

    override suspend fun updateClinic(clinic: Clinic) {
        clinicRef.document(clinic.id).set(clinic).await()
    }
}