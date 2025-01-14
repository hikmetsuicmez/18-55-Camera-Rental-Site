package com.example.camerarentalapp.storage

import android.content.Context
import com.example.camerarentalapp.model.Comment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.Date

object CommentStorage {
    private const val FILE_NAME = "comments.json"
    private val comments = mutableListOf<Comment>()


    fun loadCommentsFromFile(context: Context): MutableList<Comment> {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return mutableListOf()

        val json = file.readText()
        val listType = object : TypeToken<MutableList<Comment>>() {}.type
        return Gson().fromJson(json, listType) ?: mutableListOf()
    }

    fun saveCommentsToFile(context: Context, comments: MutableList<Comment>) {
        val file = File(context.filesDir, FILE_NAME)
        val json = Gson().toJson(comments)
        file.writeText(json)
    }

    fun getCommentsForPhoto(photoId: String): List<Comment> {
        return comments.filter { it.photoId == photoId }
    }

    fun addComment(photoId: String, text: String) {
        val newComment = Comment(
            userId = (comments.size + 1).toString(),
            photoId = photoId,
            username = "Kullanıcı",
            commentText = text,
        )
        comments.add(newComment)
    }
}