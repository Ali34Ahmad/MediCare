package com.example.doctor.data.model.user

import com.google.firebase.firestore.DocumentId

data class UserAccount(
    @DocumentId
    val id : String ,
    val email : String,
)