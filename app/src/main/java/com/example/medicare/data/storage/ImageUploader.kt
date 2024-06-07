package com.example.medicare.data.storage

import android.net.Uri
import dagger.Binds
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


interface ImageUploader {
    suspend fun uploadImage(uri: Uri, name: String) : Uri?
}