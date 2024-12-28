package com.example.camerarentalapp.model

data class Product(
    val name: String,
    val contactInfo: String,
    val daysAndPrices: List<Pair<String, String>>,
    var imageResource: String? = null, // Fotoğraf varsa kaydetmek için
    val category: String
)
