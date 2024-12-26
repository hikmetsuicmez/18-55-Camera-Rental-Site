package com.example.camerarentalapp.ui.rental

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.camerarentalapp.R
import com.example.camerarentalapp.ui.components.BottomNavigationBar

@Composable
fun RentalPageScreen(navController: NavController) {
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

            Spacer(modifier = Modifier.height(16.dp))

            // Equipment Grid
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    EquipmentCard(
                        navController = navController,
                        imageRes = R.drawable.camera_icon,
                        label = "KAMERA",
                        onClick = { navController.navigate("cameraPage") }
                    )
                    EquipmentCard(
                        navController = navController,
                        imageRes = R.drawable.lens_icon,
                        label = "LENS",
                        onClick = { navController.navigate("lensPage") }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    EquipmentCard(
                        navController = navController,
                        imageRes = R.drawable.tripod_icon,
                        label = "TRIPOD",
                        onClick = { navController.navigate("tripodPage") }
                    )
                    EquipmentCard(
                        navController = navController,
                        imageRes = R.drawable.light_icon,
                        label = "IŞIK",
                        onClick = { navController.navigate("lightPage") }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        // Bottom Navigation
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
fun EquipmentCard(
    navController: NavController,
    imageRes: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(140.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = label,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .shadow(elevation = 14.dp, shape = RoundedCornerShape(16.dp))
                .background(Color(0xFFDDDDDD), shape = RoundedCornerShape(12.dp))
                .padding(vertical = 4.dp, horizontal = 12.dp)
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Black,
            )
        }
    }
}
