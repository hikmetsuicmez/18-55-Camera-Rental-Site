package com.example.camerarentalapp.storage

import android.content.Context
import android.content.SharedPreferences
import com.example.camerarentalapp.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProductStorage {

    fun saveProduct(context: Context, product: Product) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("products", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Mevcut ürünleri JSON'dan al
        val existingProductsJson = sharedPreferences.getString("product_list", "[]")
        val type = object : TypeToken<MutableList<Product>>() {}.type
        val productList: MutableList<Product> = Gson().fromJson(existingProductsJson, type)

        // Yeni ürünü listeye ekle
        productList.add(product)

        // Güncellenmiş listeyi JSON olarak kaydet
        val updatedJson = Gson().toJson(productList)
        editor.putString("product_list", updatedJson)
        editor.apply()
    }
}