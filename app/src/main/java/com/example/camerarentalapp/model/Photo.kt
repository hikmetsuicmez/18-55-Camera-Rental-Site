package com.example.camerarentalapp.model

data class Photo(
    val id: String = "",
    var uri: String? = null,
    val name: String,
    val equipmentInfo: String
)
