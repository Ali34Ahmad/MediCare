package com.example.medicare.data.storage.impl

import android.net.Uri
import com.example.medicare.data.storage.ImageUploader
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ImageUploaderImpl @Inject constructor(
    database: FirebaseStorage
) : ImageUploader {

    private val storageRef = database.reference

    override suspend fun uploadImage(uri: Uri, name: String): Uri? {
        val imageRef = storageRef.child("image/$name.jpg")
        val uploadTask = imageRef.putFile(uri).await()
        return imageRef.downloadUrl.await()
    }

}