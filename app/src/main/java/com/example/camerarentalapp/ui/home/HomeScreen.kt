package com.example.camerarentalapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.camerarentalapp.R

@Composable
fun HomeScreen(navController: NavController) {
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

            // Butonları Ortalamak İçin Boşluk
            Spacer(modifier = Modifier.weight(1f))

            // Butonlar
            Button(
                onClick = { navController.navigate("login") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RectangleShape,
                modifier = Modifier
                    .width(313.dp)
                    .height(136.dp)
                    .background(Color.LightGray, shape = RectangleShape)
            ) {
                Text(
                    text = "Profil",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = { navController.navigate("register") },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RectangleShape,
                modifier = Modifier
                    .width(313.dp)
                    .height(136.dp)
                    .background(Color.LightGray, shape = RectangleShape)
            ) {
                Text(
                    text = "Ekipmanlar",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(35.dp))


            // Alt Kısmı Doldurmak İçin Boşluk
            Spacer(modifier = Modifier.weight(1f))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .background(Color(0xFFFF2424))
                .align(Alignment.BottomCenter)
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
                    Image(
                        painter = painterResource(id = R.drawable.ic_menu), // Replace with correct icon
                        contentDescription = "Menu",
                        modifier = Modifier
                            .width(70.dp)
                            .height(70.dp)
                    )
                    Text(
                        text = "Menü",
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }

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
                                .clickable { navController.navigate("gallery") }
                                .align(Alignment.Center)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_home), // Replace with correct icon
                                contentDescription = "Galeri",
                                modifier = Modifier
                                    .size(55.dp)
                                    .background(Color(0xFFC91B1B), shape = CircleShape)
                                    .align(Alignment.Center)
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
                        modifier = Modifier.size(70.dp)
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
}
