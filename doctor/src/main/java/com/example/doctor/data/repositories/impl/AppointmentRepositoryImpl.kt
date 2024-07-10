package com.example.doctor.data.repositories.impl

import com.example.doctor.core.constants.DatabaseCollections
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.repositories.AppointmentRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val database: FirebaseFirestore,
    private val auth: FirebaseAuth
) : AppointmentRepository {

    private val currentUserId: String?
        get() = auth.currentUser?.uid


    private val appointmentsRef = database.collection(DatabaseCollections.APPOINTMENTS_COLLECTION)

    override val appointments: Flow<List<Appointment>>
        get() = appointmentsRef.whereEqualTo("clinic.responsibleDoctor.id", currentUserId ?: "")
            .snapshots().map { snapshot ->
                snapshot.toObjects(Appointment::class.java)
            }
    override val appointmentNumber: Flow<Int>
        get() = appointments.map {
            it.size
        }

    override suspend fun addAppointment(appointment: Appointment) {
        appointmentsRef.add(appointment).await()
    }

    override suspend fun deleteAppointment(id: String) {
        appointmentsRef.document(id).delete().await()
    }

    override suspend fun getNumberOfAppointments(userId: String): Int {
        return appointmentsRef
            .get()
            .await()
            .size()
    }

    override suspend fun getAppointmentsByDate(date: FullDate): Flow<List<Appointment>> =
        callbackFlow {

            var snapshotListener: ListenerRegistration? = null

            snapshotListener = appointmentsRef.addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error) // Close the flow with the error
                    return@addSnapshotListener
                }
                val appointments = snapshot?.toObjects(Appointment::class.java) ?: emptyList()
                val filteredAppointments = appointments.filter { it.date == date }
                trySend(filteredAppointments)
            }
            awaitClose {
                snapshotListener.remove() // Clean up the listener on flow completion
            }
        }.flowOn(Dispatchers.IO)

}