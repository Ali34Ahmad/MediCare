package com.example.doctor.data.repositories.impl

import com.example.doctor.core.constants.DatabaseCollections
import com.example.doctor.data.model.vaccine.Vaccine
import com.example.doctor.data.repositories.VaccineRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class VaccineRepositoryImpl @Inject constructor(
    database: FirebaseFirestore
): VaccineRepository {

    private val vaccinesRef = database.collection(
        DatabaseCollections.VACCINES_COLLECTION
    )
    override suspend fun addVaccine(vaccine: Vaccine) {
        vaccinesRef.add(vaccine).await()
    }
    override val vaccines: Flow<List<Vaccine>>
        get() = vaccinesRef.snapshots().map{
            it.toObjects(Vaccine::class.java)
        }
}


//vaccinesRef.document("MyUniqueID").set(vaccine)
