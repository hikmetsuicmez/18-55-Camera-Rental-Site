package com.example.camerarentalapp.storage

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.camerarentalapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

object ProductStorage {

    fun saveProduct(context: Context, product: Product) {
        val sharedPreferences = context.getSharedPreferences("products", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val existingProductsJson = sharedPreferences.getString("product_list", "[]") ?: "[]"
        val type = object : TypeToken<MutableList<Product>>() {}.type
        val productList: MutableList<Product> = Gson().fromJson(existingProductsJson, type)

        println("Kaydedilecek Ürün: $product")
        val productToSave = product.copy(
            daysAndPrices = product.daysAndPrices.toList() // Bu satır kritik
        )

        try {
            product.imageResource?.let { uri ->
                val inputStream = context.contentResolver.openInputStream(Uri.parse(uri))
                val outputFile = File(context.filesDir, "product_${System.currentTimeMillis()}.jpg")

                val parentDir = outputFile.parentFile
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs() // Dizin yoksa oluştur
                }

                inputStream.use { input ->
                    outputFile.outputStream().use { output ->
                        input?.copyTo(output)
                    }
                }

                product.imageResource = outputFile.absolutePath // Kalıcı path'i kaydedin
            }
        } catch (e: Exception) {
            Log.e("saveProduct", "Error saving image: ${e.message}")
        }

        println("Kaydedilecek Dönüştürülmüş Ürün: $productToSave")
        productList.add(productToSave)
        println("Kaydedilen ürün: $productToSave")

        val updatedJson = Gson().toJson(productList)
        println("Updated JSON: $updatedJson") // Kaydedilen JSON'u loglayın
        editor.putString("product_list", updatedJson)
        editor.apply()
    }


    fun getProductsByCategory(context: Context, category: String): List<Product> {
        val sharedPreferences = context.getSharedPreferences("products", Context.MODE_PRIVATE)
        val existingProductsJson = sharedPreferences.getString("product_list", "[]") ?: "[]"
        println("JSON'dan Gelen Ürünler: $existingProductsJson") // JSON versiyonunu loglayın

        val type = object : TypeToken<List<Product>>() {}.type
        val productList: List<Product> = Gson().fromJson(existingProductsJson, type)
        println("Ürün Listesi: $productList") // Log

        // Genel bir kategori eşleştirme mantığı
        val filteredProducts = productList.filter {
            it.category.equals(category, ignoreCase = true)
        }
        println("Filtrelenmiş Ürünler: $filteredProducts") // Log

        return filteredProducts
    }






}