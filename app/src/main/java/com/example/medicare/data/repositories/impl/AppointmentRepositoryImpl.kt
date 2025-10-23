package com.example.medicare.data.repositories.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.data.repositories.AppointmentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    database : FirebaseFirestore,
    private val auth: FirebaseAuth
) : AppointmentRepository {

    private val currentUserId :String?
        get() = auth.currentUser?.uid

    private val appointmentsRef = database.collection(DatabaseCollections.APPOINTMENTS_COLLECTION)


    override suspend fun addAppointment(appointment: Appointment) {
        appointmentsRef.add(appointment).await()
    }

    override suspend fun deleteAppointment(id: String) {
        appointmentsRef.document(id).delete().await()
    }

    override val appointments: Flow<List<Appointment>>
        get() = appointmentsRef.whereEqualTo("userId", currentUserId?:"").snapshots().map { snapshot ->
            snapshot.toObjects(Appointment::class.java)
        }

    override suspend fun getNumberOfAppointments(userId: String): Int {
        return appointmentsRef
            .whereEqualTo("userId", userId)
            .get()
            .await()
            .size()
    }

    override fun vaccineAppointments(ids: List<String>): Flow<List<Appointment>> =
        appointmentsRef
            .whereEqualTo("clinic.name","Vaccines")
            .whereIn("userId",ids)
            .snapshots().map { snapshot ->
                snapshot.toObjects(Appointment::class.java)
            }
}