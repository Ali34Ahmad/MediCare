package com.example.medicare.data.repositories.impl

import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.repositories.ChildRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChildRepositoryImpl @Inject constructor(
    database: FirebaseFirestore,
    auth: FirebaseAuth
) : ChildRepository {

    private val usersRef = database.collection(DatabaseCollections.USERS_COLLECTION)
    private val currentUserId = auth.currentUser?.uid ?: "0"
    private val childrenRef = usersRef.document(currentUserId).collection(DatabaseCollections.CHILDREN_COLLECTION)

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

}