package com.example.camerarentalapp.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun WelcomeScreen(navController: NavController) {
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
            Spacer(modifier = Modifier.weight(0.6f))

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
                    text = "Giriş Yap",
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
                    text = "Kayıt Ol",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(35.dp))

            Button(
                onClick = { /* Navigate to Explore */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RectangleShape,
                modifier = Modifier
                    .width(313.dp)
                    .height(136.dp)
                    .background(Color.LightGray, shape = RectangleShape)
            ) {
                Text(
                    text = "Keşfer",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }

            // Alt Kısmı Doldurmak İçin Boşluk
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}