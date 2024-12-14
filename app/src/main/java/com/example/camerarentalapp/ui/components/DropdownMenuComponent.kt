package com.example.camerarentalapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.camerarentalapp.R

@Composable
fun DropdownMenuComponent(navController: NavController) {
    var isMenuOpen by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu",
                modifier = Modifier
                    .size(60.dp)
                    .clickable { isMenuOpen = !isMenuOpen }
            )

            DropdownMenu(
                expanded = isMenuOpen,
                onDismissRequest = { isMenuOpen = false },
                modifier = Modifier
                    .background(Color(0xFFD9D9D9), shape = RoundedCornerShape(8.dp))
                    .width(160.dp)
                    .height(200.dp)
            ) {
                // Kiralama
                DropdownMenuItemCard(
                    text = "Kiralama",
                    icon = R.drawable.ic_rental,
                    navController = navController,
                    route = "equipment"
                )
                // Kurslar
                DropdownMenuItemCard(
                    text = "Kurslar",
                    icon = R.drawable.ic_course,
                    navController = navController,
                    route = "course"
                )
                // Etkinlikler
                DropdownMenuItemCard(
                    text = "Etkinlikler",
                    icon = R.drawable.ic_events,
                    navController = navController,
                    route = "activity"
                )
                // Ayarlar
                DropdownMenuItemCard(
                    text = "Ayarlar",
                    icon = R.drawable.ic_settings,
                    navController = navController,
                    route = null
                )
            }
        }
    }
}

@Composable
fun DropdownMenuItemCard(
    text: String,
    icon: Int,
    navController: NavController,
    route: String?
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .height(36.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF656565))
    ) {
        DropdownMenuItem(
            text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = text,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text, color = Color.White, fontSize = 14.sp)
                }
            },
            onClick = {
                if (route != null) {
                    navController.navigate(route)
                }
            }
        )
    }
}
