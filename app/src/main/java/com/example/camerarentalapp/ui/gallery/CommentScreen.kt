package com.example.camerarentalapp.ui.comments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.camerarentalapp.model.Comment
import com.example.camerarentalapp.storage.CommentStorage

@Composable
fun CommentScreen(navController: NavController, photoId: String) {
    // Get the list of comments for the given photo ID
    val comments = remember { CommentStorage.getCommentsForPhoto(photoId) }
    var newCommentText by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Comments List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp)
        ) {
            items(comments) { comment ->
                CommentItem(comment)
            }
        }

        // Add New Comment Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newCommentText,
                onValueChange = { newCommentText = it },
                modifier = Modifier.weight(1f),
                label = { Text("Yorum yaz...") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newCommentText.text.isNotBlank()) {
                        CommentStorage.addComment(
                            photoId = photoId,
                            text = newCommentText.text
                        )
                        newCommentText = TextFieldValue("") // Clear input
                    }
                }
            ) {
                Text("Gönder")
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = comment.username, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = comment.commentText, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
