package com.example.camerarentalapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.camerarentalapp.R

@Composable
fun BottomNavigationBar(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .background(Color(0xFFFF2424))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Menu Button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DropdownMenuComponent(navController)
                Text(
                    text = "Menü",
                    fontSize = 12.sp,
                    color = Color.White
                )
            }

            // Home Button (Gallery)
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color(0xFFFF2424), shape = CircleShape)
                    .offset(y = (-15).dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color.White, shape = CircleShape)
                        .align(Alignment.Center)
                        .padding(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0xFFC91B1B), shape = CircleShape)
                            .clickable { navController.navigate("home") }
                            .align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_home), // Replace with correct icon
                            contentDescription = "Galeri",
                            modifier = Modifier
                                .size(55.dp)
                                .background(Color(0xFFC91B1B), shape = CircleShape)
                                .align(Alignment.Center),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            // Profile Button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile), // Replace with correct icon
                    contentDescription = "Profil",
                    modifier = Modifier
                        .size(70.dp)
                        .clickable { navController.navigate("profile") }
                )
                Text(
                    text = "Profil",
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}
