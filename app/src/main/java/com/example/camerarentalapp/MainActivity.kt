package com.example.camerarentalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.camerarentalapp.ui.auth.LoginScreen
import com.example.camerarentalapp.ui.auth.RegisterScreen
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

        composable("welcome") {
            WelcomeScreen(navController)
        }

        composable("login") {
            LoginScreen(onNavigateToRegister = {
                navController.navigate("register")
            })
        }

        composable("register") {
            RegisterScreen(onNavigateBack = {
                navController.popBackStack()
            })
        }

    }
}
