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

@Composable
fun RegisterScreen(navController: NavController, onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var registerMessage by remember { mutableStateOf("") }

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
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF2424)),
                    onClick = {
                        navController.navigate("login")
                        // Boş alan kontrolü
                        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                            registerMessage = "All fields must be filled"
                            return@Button
                        }

                        // E-posta formatı kontrolü
                        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
                        if (!emailRegex.matches(email)) {
                            registerMessage = "Invalid email format"
                            return@Button
                        }

                        // Şifre eşleşme kontrolü
                        if (password != confirmPassword) {
                            registerMessage = "Passwords do not match"
                            return@Button
                        }

                        if (password.length < 6) {
                            registerMessage = "Password must be at least 6 characters long"
                            return@Button
                        }

                        // E-posta var mı kontrolü
                        val users = UserStorage.loadUsersFromFile(context)
                        if (users.any { it.email == email }) {
                            registerMessage = "Registration Failed: Email already exists"
                            return@Button
                        }

                        // Yeni kullanıcı oluştur
                        val newUser = User(email = email, password = password)
                        users.add(newUser)
                        UserStorage.saveUsersToFile(context, users)
                        registerMessage = "User registered: $email"
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Register")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(registerMessage)

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC91B1B)),
                    onClick = onNavigateBack
                )
                {
                    Text("Back to Login")
                }
            }
        }
    }
}