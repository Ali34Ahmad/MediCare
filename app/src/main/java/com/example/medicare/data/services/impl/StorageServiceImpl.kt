package com.example.medicare.data.services.impl

import android.net.Uri
import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.user.Doctor
import com.example.medicare.data.model.user.User
import com.example.medicare.data.model.vaccine.Vaccine
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.services.StorageService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth,
    storage : FirebaseStorage
) : StorageService {
    // Get the current user's ID
    private val currentUserId = auth.currentUser?.uid ?:"0"
    // Get references for all collections
    private val usersRef = firestore.collection(DatabaseCollections.USERS_COLLECTION)
    private val childrenRef = usersRef.document(currentUserId).collection(DatabaseCollections.CHILDREN_COLLECTION)
    private val vaccinesRef = firestore.collection(DatabaseCollections.VACCINES_COLLECTION)
    private val doctorRef = firestore.collection(DatabaseCollections.DOCTORS_COLLECTION)
    private val clinicRef = firestore.collection(DatabaseCollections.CLINICS_COLLECTION)
    private val appointmentsRef = firestore.collection(DatabaseCollections.APPOINTMENTS_COLLECTION)
    private val storageRef = storage.reference
    override suspend fun addNewUser(user: User) {
        usersRef.document(currentUserId).set(user).await()
    }

    override suspend fun getUser(): User? {
        return usersRef.document(currentUserId).get().await().toObject(User::class.java)
    }

    override suspend fun addChild(child: Child) {
       childrenRef.add(child).await()
    }
    override suspend fun getVaccineTable(childId: String): List<VaccineTableItem> {
       return childrenRef.document(childId).collection(DatabaseCollections.VACCINE_TABLE_COLLECTION)
           .get().await().toObjects(VaccineTableItem::class.java)
    }

    override val children: Flow<List<Child>>
        get() = childrenRef.snapshots().map { snapshot->
            snapshot.toObjects(Child::class.java)
        }

    override suspend fun addVaccineTableItem(vaccineTableItem: VaccineTableItem, childId: String) {
        childrenRef.document(childId).collection(DatabaseCollections.VACCINE_TABLE_COLLECTION)
            .document(vaccineTableItem.vaccine.id).set(vaccineTableItem).await()
    }

    override suspend fun addVaccine(vaccine: Vaccine) {
        vaccinesRef.add(vaccine).await()
    }


    override val vaccines: Flow<List<Vaccine>>
        get() = vaccinesRef.snapshots().map {snapshot ->
            snapshot.toObjects(Vaccine::class.java)
        }


    override suspend fun addDoctor(doctor: Doctor) {
        doctorRef.add(doctor).await()
    }

    override suspend fun createNewClinic(clinic: Clinic) {
        clinicRef.add(clinic).await()
    }

    override val clinics: Flow<List<Clinic>>
        get() = clinicRef.snapshots().map {snapshot ->
                snapshot.toObjects(Clinic::class.java)
            }

    override suspend fun addClinic(clinic: Clinic) {
        clinicRef.add(clinic).await()
    }

    override suspend fun addAppointment(appointment: Appointment) {
        appointmentsRef.add(appointment).await()
    }

    override val appointments: Flow<List<Appointment>>
        get() = appointmentsRef.whereEqualTo("userId",currentUserId).snapshots().map { snapshot ->
            snapshot.toObjects(Appointment::class.java)
        }

     suspend fun uploadPhoto(uri: Uri, name: String): Uri? {
        val imageRef = storageRef.child("image/$name.jpg")
        imageRef.putFile(uri).await()
        return imageRef.downloadUrl.await()
    }
}
