package com.example.medicare.data.repositories.impl

import android.util.Log
import com.example.medicare.core.constants.DatabaseCollections
import com.example.medicare.data.childTable.ChildTable
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.child.VaccineTableItem
import com.example.medicare.data.repositories.ChildRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChildRepositoryImpl @Inject constructor(
    database: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val childTable:ChildTable
) : ChildRepository {

    private val usersRef = database.collection(DatabaseCollections.USERS_COLLECTION)
    private val currentUserId :String?
        get() = auth.currentUser?.uid

    private val childrenRef get()= currentUserId?.let {
        usersRef.document(it)
        .collection(DatabaseCollections.CHILDREN_COLLECTION)
    }


    override suspend fun addChild(child: Child) = try{
        val id = childrenRef?.add(child)?.await()?.id
        if (id != null) {
            childTable.initTable(id)
            true
        }
        false
    }catch (e: Exception){
        Log.e("ChildRepositoryImpl","Error adding new child: ${e.message}")
        false
    }

    override suspend fun getVaccineTable(childId: String): List<VaccineTableItem> {


        return childrenRef?.document(childId)?.collection(DatabaseCollections.VACCINE_TABLE_COLLECTION)
            ?.get()?.await()?.toObjects(VaccineTableItem::class.java)
            ?: emptyList()
    }

    override val children: Flow<List<Child>>
        get() = childrenRef?.snapshots()?.map { snapshot->
            snapshot.toObjects(Child::class.java)
        }?: flowOf(emptyList())

    override suspend fun addVaccineTableItem(vaccineTableItem: VaccineTableItem, childId: String) {
        childrenRef?.document(childId)?.collection(DatabaseCollections.VACCINE_TABLE_COLLECTION)
            ?.document(vaccineTableItem.vaccine.id)?.set(vaccineTableItem)?.await()
    }

    override suspend fun getChildrenIds() :List<String>{
        val ids =  childrenRef?.get()?.await()?.toObjects(Child::class.java)?.map { it.id }
        return ids?:emptyList()
    }
}