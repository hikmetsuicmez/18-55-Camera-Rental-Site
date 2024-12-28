package com.example.camerarentalapp.ui.rental

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
fun EquipmentListScreen(navController: NavController, category: String) {
    val products = remember { mutableStateOf<List<Product>>(emptyList()) }

    LaunchedEffect(category) {
        val productsByCategory =
            ProductStorage.getProductsByCategory(navController.context, category)
        products.value = productsByCategory
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp), // Bottom Navigation Bar için boşluk
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sabit Logo Kısmı
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

            Spacer(modifier = Modifier.height(8.dp))

            // Ürün Listesi
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(products.value) { product ->
                    ProductCard(product = product, onClick = {
                        navController.navigate("home")
                        println("Tıklandı: ${product.name}")
                    })
                }
            }

        }
        // Bottom Navigation Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomNavigationBar(navController)
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        // Main Card with Image
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp) // Reduced height from 180dp
                .clickable { onClick() },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)) // Ensure image corners match card
            ) {
                // Product Image
                if (!product.imageResource.isNullOrEmpty()) {
                    val file = File(product.imageResource!!)
                    if (file.exists()) {
                        Image(
                            painter = rememberAsyncImagePainter(model = file),
                            contentDescription = product.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        PlaceholderBox()
                    }
                } else {
                    PlaceholderBox()
                }

                // Action Buttons Column
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    ActionButton(
                        icon = Icons.Default.Image,
                        text = "Galeri",
                        onClick = { /* Handle gallery click */ }
                    )
                    ActionButton(
                        icon = Icons.Default.Comment,
                        text = "Yorumlar",
                        onClick = { /* Handle comments click */ }
                    )
                    ActionButton(
                        icon = Icons.Default.AttachMoney,
                        text = "Fiyat Bilgisi",
                        onClick = { /* Handle price info click */ }
                    )
                    ActionButton(
                        icon = Icons.Default.Call,
                        text = "İletişim",
                        onClick = { /* Handle contact click */ }
                    )
                }
            }
        }

        // Product Name Card
        Card(
            modifier = Modifier
                .align(Alignment.Start),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD0D0D0))
        ) {
            Text(
                text = product.name,
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
private fun PlaceholderBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    )
}

@Composable
private fun ActionButton(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(24.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(4.dp),
        color = Color.White.copy(alpha = 0.9f)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.Black,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = text,
                fontSize = 10.sp,
                color = Color.Black
            )
        }
    }
}


