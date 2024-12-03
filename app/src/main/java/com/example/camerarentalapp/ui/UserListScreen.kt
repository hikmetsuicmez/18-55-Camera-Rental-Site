package com.example.camerarentalapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.camerarentalapp.model.User
import com.example.camerarentalapp.model.UserRepository
import com.example.camerarentalapp.storage.UserStorage

@Composable
fun UserListScreen(onNavigateBack: () -> Unit) {
    val context = LocalContext.current
    val users = UserRepository.userList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registered Users",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (users.isEmpty()) {
            Text(
                text = "No users registered yet.",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(users.size) { index ->
                    val user = users[index]
                    UserListItem(user)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val exportMessage = UserStorage.loadUsersFromFile(context)
                println(exportMessage) // Dosyanın kaydedildiği yeri Logcat'te göster
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Export Users to JSON")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Main")
        }
    }
}

@Composable
fun UserListItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Password: ${user.password}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
