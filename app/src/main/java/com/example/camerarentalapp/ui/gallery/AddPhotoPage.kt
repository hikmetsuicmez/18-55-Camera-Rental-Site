package com.example.camerarentalapp.ui.gallery

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.camerarentalapp.model.Photo
import com.example.camerarentalapp.storage.PhotoStorage
import com.example.camerarentalapp.storage.UserStorage
import com.example.camerarentalapp.ui.components.BottomNavigationBar

@Composable
fun AddPhotoPage(navController: NavController) {
    var selectedImageUri by remember { mutableStateOf<String?>(null) }
    var equipmentInfo by remember { mutableStateOf("") }
    var nameInfo by remember { mutableStateOf(UserStorage.currentUser?.fullName ?: "") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri = uri?.toString()
    }

    Box(modifier = Modifier.fillMaxSize()) {
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
                .padding(bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
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

            Spacer(modifier = Modifier.height(91.dp))

            // Photo Selection
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(340.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                selectedImageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(model = it),
                        contentDescription = "Selected Photo",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } ?: Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.add_photo_icon),
                        contentDescription = "Add Photo",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Fotoğraf Ekle", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name Field
            TextField(
                value = nameInfo,
                onValueChange = { nameInfo = it },
                placeholder = { Text("İsim Soyisim") },
                modifier = Modifier.width(300.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFD9D9D9),
                    unfocusedContainerColor = Color(0xFFD9D9D9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Equipment Info Field
            TextField(
                value = equipmentInfo,
                onValueChange = { equipmentInfo = it },
                placeholder = { Text("Ekipman Bilgisi") },
                modifier = Modifier.width(300.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFD9D9D9),
                    unfocusedContainerColor = Color(0xFFD9D9D9),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(112.dp))

            // Share Button
            Button(
                onClick = {
                    if (selectedImageUri != null && equipmentInfo.isNotEmpty()) {
                        val photo = Photo(
                            uri = selectedImageUri!!,
                            name = nameInfo,
                            equipmentInfo = equipmentInfo
                        )
                        PhotoStorage.savePhoto(navController.context, photo)
                        navController.popBackStack()
                        navController.navigate("photoGallery")
                    } else {
                        Toast.makeText(
                            navController.context,
                            "Lütfen tüm bilgileri doldurun!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(horizontal = 16.dp)
                    .height(34.dp)
                    .width(100.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text("Paylaş", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
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
    }
}
