package com.example.doctor.data.storage.impl

import android.net.Uri
import com.example.doctor.data.storage.ImageUploader
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageUploaderImpl @Inject constructor(
    database: FirebaseStorage
) : ImageUploader {

    private val storageRef = database.reference

    override suspend fun uploadImage(uri: Uri, name: String): String {
        val imageRef = storageRef.child("images/$name.jpg")
        imageRef.putFile(uri).await()
        return imageRef.downloadUrl.await().toString()
    }
}
