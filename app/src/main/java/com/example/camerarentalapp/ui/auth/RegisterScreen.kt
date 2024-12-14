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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.camerarentalapp.R
import com.example.camerarentalapp.model.User
import com.example.camerarentalapp.storage.UserStorage
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(navController: NavController, onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var registerMessage by remember { mutableStateOf("") }
    var registerSuccesful by remember { mutableStateOf(false) }


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

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Şifreyi Onayla") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2424)),
                    onClick = {
                        // Boş alan kontrolü
                        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                            registerMessage = "Tüm alanlar doldurulmalıdır."
                            return@Button
                        }

                        // E-posta formatı kontrolü
                        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
                        if (!emailRegex.matches(email)) {
                            registerMessage = "Geçersiz e-posta biçimi"
                            return@Button
                        }

                        // Şifre eşleşme kontrolü
                        if (password != confirmPassword) {
                            registerMessage = "Şifreler eşleşmiyor"
                            return@Button
                        }

                        if (password.length < 6) {
                            registerMessage = "Şifre en az 6 karakter uzunluğunda olmalıdır"
                            return@Button
                        }

                        // E-posta var mı kontrolü
                        val users = UserStorage.loadUsersFromFile(context)
                        if (users.any { it.email == email }) {
                            registerMessage = "Kayıt Başarısız Oldu: E-posta zaten mevcut"
                            return@Button
                        }

                        // Yeni kullanıcı oluştur
                        val newUser = User(email = email, password = password)
                        users.add(newUser)
                        UserStorage.saveUsersToFile(context, users)

                        if (newUser != null) {
                            registerMessage = "Kayıtlı Kullanıcı : $email"
                            registerSuccesful = true
                        } else {
                            registerMessage = "Kayıt Başarısız oldu."
                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Kayıt Ol")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(registerMessage)

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC91B1B)),
                    onClick = onNavigateBack
                )
                {
                    Text("Giriş sayfasına dön.")
                }
            }
        }
    }

    if (registerSuccesful) {
        LaunchedEffect(key1 = registerSuccesful) {
            delay(700)
            navController.navigate("login")
        }
    }
}