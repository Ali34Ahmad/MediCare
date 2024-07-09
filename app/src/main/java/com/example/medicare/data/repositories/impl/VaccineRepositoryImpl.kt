package com.example.medicare.data.repositories.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.data.repositories.VaccineRepository
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
