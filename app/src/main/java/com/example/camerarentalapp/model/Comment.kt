package com.example.camerarentalapp.model

data class Comment(
    val photoId: String,
    val userId: String,
    val username: String,
    val commentText: String,
)
