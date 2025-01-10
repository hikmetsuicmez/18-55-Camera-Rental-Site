package com.example.camerarentalapp.storage

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.camerarentalapp.model.Photo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object PhotoStorage {

    private const val FILE_NAME = "photos.json"

    fun savePhoto(context: Context, photo: Photo) {
        val photos = getAllPhotos(context).toMutableList()

        try {
            // Fotoğrafı dosya olarak kaydetme
            photo.uri?.let { uri ->
                val inputStream = context.contentResolver.openInputStream(Uri.parse(uri))
                val outputFile = File(context.filesDir, "photo_${System.currentTimeMillis()}.jpg")

                // Dosya dizini oluşturma
                val parentDir = outputFile.parentFile
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs()
                }

                inputStream.use { input ->
                    outputFile.outputStream().use { output ->
                        input?.copyTo(output)
                    }
                }

                // Kalıcı dosya yolunu URI olarak kaydet
                photo.uri = outputFile.absolutePath
            }
        } catch (e: Exception) {
            Log.e("PhotoStorage", "Error saving photo file: ${e.message}")
        }

        photos.add(photo)
        savePhotosToFile(context, photos)
    }

    fun getAllPhotos(context: Context): List<Photo> {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return emptyList()

        return try {
            val json = file.readText()
            Gson().fromJson(json, object : TypeToken<List<Photo>>() {}.type)
        } catch (e: Exception) {
            Log.e("PhotoStorage", "Error reading photos: ${e.message}")
            emptyList()
        }
    }

    private fun savePhotosToFile(context: Context, photos: List<Photo>) {
        val file = File(context.filesDir, FILE_NAME)
        file.writeText(Gson().toJson(photos))
    }
}
