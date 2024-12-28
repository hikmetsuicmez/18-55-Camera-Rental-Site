package com.example.camerarentalapp.storage

import android.content.Context
import com.example.camerarentalapp.model.User
import com.example.camerarentalapp.model.UserRepository
import com.google.gson.Gson
import java.io.File

object UserStorage {
    private const val JSON_FILE_NAME = "users.json"

    fun initializeUsers(context: Context) {
        val loadedUsers = loadUsersFromFile(context)
        UserRepository.userList.clear()
        UserRepository.userList.addAll(loadedUsers)
    }

    fun saveUsersToFile(context: Context, users: List<User>) {
        val gson = Gson()
        val json = gson.toJson(users)

        val exportPath = File(context.filesDir, JSON_FILE_NAME)
        exportPath.writeText(json)

        println("User data exported to: ${exportPath.absolutePath}") // Logcat'te göster
    }

    // JSON formatındaki dosyayı yükle
    fun loadUsersFromFile(context: Context): MutableList<User> {
        val file = File(context.filesDir, JSON_FILE_NAME)
        return if (file.exists()) {
            val json = file.readText()
            val gson = Gson()
            val type = object : com.google.gson.reflect.TypeToken<MutableList<User>>() {}.type
            gson.fromJson(json, type) ?: mutableListOf()
        } else {
            mutableListOf()
        }
    }
}
