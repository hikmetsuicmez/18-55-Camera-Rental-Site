package com.example.camerarentalapp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.camerarentalapp.R
import com.example.camerarentalapp.storage.UserStorage
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navController: NavController, onNavigateToRegister: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginMessage by remember { mutableStateOf("") }
    var loginSuccessful by remember { mutableStateOf(false) }

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
                .fillMaxSize(),
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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-Posta") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Şifre") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2424)),
                    onClick = {
                        val users = UserStorage.loadUsersFromFile(context)
                        val user = users.find { it.email == email && it.password == password }

                        if (user != null) {
                            loginMessage = "Giriş Başarılı: ${user.email}"
                            loginSuccessful = true // Başarılı giriş durumu
                        } else {
                            loginMessage = "Giriş Başarısız Oldu: Yanlış e-posta veya şifre"
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Giriş Yap")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(loginMessage)

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC91B1B)),
                    onClick = onNavigateToRegister,
                ) {
                    Text("Hesabınız yok mu? Kayıt ol")
                }
            }
        }
    }

    // Başarılı giriş durumunda gecikmeli yönlendirme
    if (loginSuccessful) {
        LaunchedEffect(key1 = loginSuccessful) {
            delay(700)
            navController.navigate("home")
        }
    }
}

