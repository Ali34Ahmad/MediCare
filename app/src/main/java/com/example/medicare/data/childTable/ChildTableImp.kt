package com.example.medicare.data.childTable
import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.model.child.VaccineTableItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChildTableImp @Inject constructor(
    database: FirebaseFirestore,
    auth: FirebaseAuth
) : ChildTable {

    private val currentUserId = auth.currentUser?.uid
    private val childRef = currentUserId?.let { id ->
        database
            .collection(DatabaseCollections.USERS_COLLECTION)
            .document(id)
            .collection(DatabaseCollections.CHILDREN_COLLECTION)
    }

    private val defaultTableRef =
        database.collection(DatabaseCollections.DEFAULT_VACCINE_TABLE_COLLECTION)

    override suspend fun initTable(childId: String) {
        val vaccines = defaultTableRef.get().await().toObjects(VaccineTableItem::class.java)
        vaccines.forEach { vaccine ->
            childRef?.document(childId)?.collection(DatabaseCollections.VACCINE_TABLE_COLLECTION)?.add(vaccine)
        }
    }

    override suspend fun addToTable(vaccine: VaccineTableItem) {
        defaultTableRef.add(vaccine).await()
    }

    override suspend fun deleteFromTable(vaccineId: String) {
        defaultTableRef.document(vaccineId).delete().await()
    }

}