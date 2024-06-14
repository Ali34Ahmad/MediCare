package com.example.medicare.data.repositories.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.repositories.AppointmentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    database : FirebaseFirestore,
    auth: FirebaseAuth
) : AppointmentRepository {
    private val currentUserId = auth.currentUser!!.uid ?: "0"

    private val appointmentsRef = database.collection(DatabaseCollections.APPOINTMENTS_COLLECTION)

    override suspend fun addAppointment(appointment: Appointment) {
        appointmentsRef.add(appointment).await()
    }

    override val appointments: Flow<List<Appointment>>
        get() = appointmentsRef.whereEqualTo("userId",currentUserId).snapshots().map { snapshot ->
            snapshot.toObjects(Appointment::class.java)
        }
}