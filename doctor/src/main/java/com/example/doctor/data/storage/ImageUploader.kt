package com.example.doctor.data.storage

import android.net.Uri

interface ImageUploader {
    suspend fun uploadImage(uri: Uri, name: String) : String
}