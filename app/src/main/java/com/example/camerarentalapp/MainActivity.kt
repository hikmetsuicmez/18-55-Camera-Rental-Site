package com.example.camerarentalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.camerarentalapp.ui.activity.ActivityScreen
import com.example.camerarentalapp.ui.auth.LoginScreen
import com.example.camerarentalapp.ui.auth.RegisterScreen
import com.example.camerarentalapp.ui.course.CourseScreen
import com.example.camerarentalapp.ui.equipment.EquipmentScreen
import com.example.camerarentalapp.ui.gallery.AddPhotoPage
import com.example.camerarentalapp.ui.gallery.GalleryScreen
import com.example.camerarentalapp.ui.gallery.PhotoGalleryPage
import com.example.camerarentalapp.ui.home.HomeScreen
import com.example.camerarentalapp.ui.profile.ProfileScreen
import com.example.camerarentalapp.ui.rental.EquipmentListScreen
import com.example.camerarentalapp.ui.rental.ProductDetailScreen
import com.example.camerarentalapp.ui.rental.RentalPageScreen
import com.example.camerarentalapp.ui.sell.SellProductPage
import com.example.camerarentalapp.ui.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {

        composable("gallery") {
            GalleryScreen(navController)
        }

        composable("addPhoto") {
            AddPhotoPage(navController)
        }

        composable("photoGallery") {
            PhotoGalleryPage(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("activity") {
            ActivityScreen(navController)
        }

        composable("course") {
            CourseScreen(navController)
        }

        composable("welcome") {
            WelcomeScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable("equipment") {
            EquipmentScreen(navController)
        }

        composable("rentalpage") {
            RentalPageScreen(navController)
        }

        composable("sellpage") {
            SellProductPage(navController)
        }

        composable("login") {
            LoginScreen(navController, onNavigateToRegister = {
                navController.navigate("register")
            })
        }

        composable("register") {
            RegisterScreen(navController, onNavigateBack = {
                navController.popBackStack()
            })
        }

        // Yeni EquipmentListScreen için rota
        composable("equipmentList/{category}") { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""
            EquipmentListScreen(navController = navController, category = category)
        }

        composable("product_detail/{productName}") { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            ProductDetailScreen(navController = navController, productName = productName)
        }


    }
}
