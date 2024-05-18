package com.example.medicare.data.model.appointment

import com.example.medicare.data.model.date.FullDate
import com.google.firebase.firestore.DocumentId

abstract class Appointment{
    @get:DocumentId
    abstract val id : String
    abstract val userId : String
    abstract val firstName : String
    abstract val lastName : String
    abstract val email : String
    abstract val date : FullDate
    abstract val time : String
}
