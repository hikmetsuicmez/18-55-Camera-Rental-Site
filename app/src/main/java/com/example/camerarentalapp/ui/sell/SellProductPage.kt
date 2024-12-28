package com.example.camerarentalapp.ui.sell

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.camerarentalapp.R
import com.example.camerarentalapp.model.Product
import com.example.camerarentalapp.storage.ProductStorage
import com.example.camerarentalapp.ui.components.BottomNavigationBar
import java.io.File

@Composable
fun SellProductPage(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var contactInfo by remember { mutableStateOf("") }
    val daysAndPrices =
        remember { mutableStateListOf(Pair("", ""), Pair("", ""), Pair("", ""), Pair("", "")) }
    var selectedImageUri by remember { mutableStateOf<String?>(null) }
    var selectedImagePath by remember { mutableStateOf<String?>(null) }
    var selectedCategory by remember { mutableStateOf("") } // Kategori seçimi için
    val categories = listOf("Kamera", "Lens", "Tripod", "Işık") // Kategori seçenekleri

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            val context = navController.context
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputFile = File(context.filesDir, "product_${System.currentTimeMillis()}.jpg")
            inputStream.use { input ->
                outputFile.outputStream().use { output ->
                    input?.copyTo(output)
                }
            }
            selectedImagePath = outputFile.absolutePath // Kalıcı path'i kaydediyoruz
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.fillMaxSize()) {
            // Logo Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.size(250.dp, 100.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Fotoğraf Ekleme Alanı
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .clickable { launcher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    selectedImagePath?.let { path ->
                        val file = File(path)
                        if (file.exists()) {
                            Image(
                                painter = rememberAsyncImagePainter(model = file),
                                contentDescription = "Selected Photo",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    } ?: Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.add_photo_icon),
                            contentDescription = "Add Photo",
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Fotoğraf ekle", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Form Fields
                TextFieldWithPlaceholder(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "İsim"
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextFieldWithPlaceholder(
                    value = contactInfo,
                    onValueChange = { contactInfo = it },
                    placeholder = "İletişim Bilgisi"
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Kategori Seçimi
                Text("Kategori Seçimi", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                var expanded by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, RoundedCornerShape(8.dp))
                        .clickable { expanded = true }
                        .padding(16.dp)
                ) {
                    Text(
                        text = if (selectedCategory.isEmpty()) "Kategori Seçin" else selectedCategory,
                        color = Color.Black
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Gün ve Fiyat Alanları
                daysAndPrices.forEachIndexed { index, pair ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextFieldWithPlaceholder(
                            value = pair.first,
                            onValueChange = { newDay ->
                                daysAndPrices[index] = pair.copy(first = newDay)
                            },
                            placeholder = "Gün",
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TextFieldWithPlaceholder(
                            value = pair.second,
                            onValueChange = { newPrice ->
                                daysAndPrices[index] = pair.copy(second = newPrice)
                            },
                            placeholder = "Fiyat",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Tamamla Butonu
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        try {


                            val product = Product(
                                name = name,
                                contactInfo = contactInfo,
                                daysAndPrices = daysAndPrices,
                                imageResource = selectedImagePath, // Kalıcı path'i kaydediyoruz
                                category = selectedCategory
                            )

                            ProductStorage.saveProduct(navController.context, product)

                            Toast.makeText(
                                navController.context,
                                "Ürün başarıyla eklendi!",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.popBackStack()
                            navController.navigate("home")
                        } catch (e: Exception) {
                            Toast.makeText(
                                navController.context,
                                "Ürün eklenirken hata oluştu: ${e.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text(
                        "tamamla",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            BottomNavigationBar(navController)
        }
    }
}


@Composable
fun TextFieldWithPlaceholder(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.LightGray, RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                color = Color.Black
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
