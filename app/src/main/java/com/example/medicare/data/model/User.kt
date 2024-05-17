package com.example.medicare.data.model

import androidx.compose.ui.res.stringResource
import com.example.medicare.R
import com.example.medicare.data.model.enums.Gender
import com.google.firebase.firestore.DocumentId

data class User(
    @DocumentId
    val id: String ,
    val email : String ,
    val firstName : String ,
    val lastName : String ,
    val gender : Gender,
    val children : List<Child> = emptyList()
)