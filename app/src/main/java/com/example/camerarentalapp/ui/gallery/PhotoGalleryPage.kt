package com.example.camerarentalapp.ui.gallery

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.camerarentalapp.R
import com.example.camerarentalapp.model.Photo
import com.example.camerarentalapp.storage.PhotoStorage
import com.example.camerarentalapp.ui.components.BottomNavigationBar

@Composable
fun PhotoGalleryPage(navController: NavController) {
    val photosList = PhotoStorage.getAllPhotos(navController.context)

    photosList.forEach { photo ->
        Log.d(
            "PhotoGallery",
            "Photo Loaded - URI: ${photo.uri}, Name: ${photo.name}, EquipmentInfo: ${photo.equipmentInfo}"
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var selectedPhoto by remember { mutableStateOf<Photo?>(null) }

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

            Spacer(modifier = Modifier.height(16.dp))

            // Photo List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 64.dp)
            ) {
                items(photosList) { photo ->
                    PhotoCard(
                        photo = photo,
                        onInfoClick = {
                            selectedPhoto = photo
                            showDialog = true
                        },
                        onCommentClick = {
                            navController.navigate("comments/${photo.id}") //
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(54.dp))
                }
            }

        }

        // BottomNavigationBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomNavigationBar(navController)
        }

        // Show dialog if needed
        if (showDialog && selectedPhoto != null) {
            ShowInfoDialog(photo = selectedPhoto!!) {
                showDialog = false
            }
        }
    }
}

@Composable
fun PhotoCard(photo: Photo, onInfoClick: () -> Unit, onCommentClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp), // Padding'i sıfır yap
        shape = RoundedCornerShape(0.dp), // Yuvarlak köşeleri kaldır
        elevation = CardDefaults.cardElevation(0.dp), // Gölgeyi sıfırla
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        )
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = photo.uri),
            contentDescription = "Shared Photo",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), // Yüksekliği sabit tut
            contentScale = ContentScale.Crop // Görüntüyü kırparak tam doldur
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Info ikonu
            Image(
                painterResource(id = R.drawable.info),
                contentDescription = "Bilgi ikonu",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onInfoClick() }
            )

            Spacer(modifier = Modifier.width(16.dp)) // İkonlar arasında boşluk

            // Comment ikonu
            Image(
                painterResource(id = R.drawable.ic_comment), // Yorum ikonu
                contentDescription = "Yorum ikonu",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onCommentClick() }
            )
        }
    }
}



@Composable
fun ShowInfoDialog(photo: Photo, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Fotoğraf Bilgisi") },
        text = {
            Column {
                Text("İsim: ${photo.name}")
                Text("Ekipman Bilgisi: ${photo.equipmentInfo}")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Tamam")
            }
        }
    )
}
