package com.example.medicare.data.storage

import android.net.Uri

interface ImageUploader {
    suspend fun uploadImage(uri: Uri, name: String) : Uri?
}