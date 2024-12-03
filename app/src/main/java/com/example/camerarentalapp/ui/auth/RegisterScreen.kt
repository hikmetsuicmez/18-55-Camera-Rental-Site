package com.example.camerarentalapp.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.camerarentalapp.model.User
import com.example.camerarentalapp.storage.UserStorage

@Composable
fun RegisterScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var registerMessage by remember { mutableStateOf("") }

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
            onClick = {
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

        TextButton(onClick = onNavigateBack) {
            Text("Back to Login")
        }
    }
}
