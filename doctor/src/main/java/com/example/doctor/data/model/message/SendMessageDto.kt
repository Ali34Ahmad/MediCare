package com.example.doctor.data.model.message

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageDto(
    val to: String = "",
    val title: String,
    val body: String
)
