package com.example.medicare.data.model.user

import com.google.firebase.firestore.DocumentId

data class UserAccount(
    @DocumentId
    val id : String ,
    val email : String,
)