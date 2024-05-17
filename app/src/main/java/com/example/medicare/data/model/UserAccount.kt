package com.example.medicare.data.model

import com.google.firebase.firestore.DocumentId

data class UserAccount(
    @DocumentId
    val id : String ,
    val email : String
)