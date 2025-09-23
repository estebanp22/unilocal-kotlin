package com.unilocal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unilocal.app.ui.NavRoutes
import com.unilocal.app.ui.screens.EditProfileScreen
import com.unilocal.app.ui.screens.HomeScreen
import com.unilocal.app.ui.screens.LoginScreen
import com.unilocal.app.ui.screens.RegisterPlaceScreen
import com.unilocal.app.ui.screens.RegisterScreen
import com.unilocal.app.ui.theme.UniLocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniLocalApp()
        }
    }
}

@Composable
fun UniLocalApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Login.route
    ) {
        composable(NavRoutes.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(NavRoutes.Register.route) },
                onNavigateToHome = { navController.navigate(NavRoutes.Home.route) }
            )
        }
        composable(NavRoutes.Register.route) {
            RegisterScreen(
                onRegister = { navController.navigate(NavRoutes.Home.route) }
            )
        }
        composable(NavRoutes.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(NavRoutes.RegisterPlace.route) {
            RegisterPlaceScreen(navController = navController)
        }
        composable(NavRoutes.EditProfile.route) {
            EditProfileScreen(onSave = {
                navController.navigate(NavRoutes.Home.route)
            }
            )
        }


    }
}

