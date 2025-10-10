package com.unilocal.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unilocal.app.ui.screens.*
import com.unilocal.app.ui.screens.admin.AdminPanelScreen
import com.unilocal.app.ui.screens.admin.RequestHistoryScreen
import com.unilocal.app.ui.screens.admin.RequestListScreen
import com.unilocal.app.ui.screens.user.EditProfileScreen
import com.unilocal.app.ui.screens.user.HomeScreen
import com.unilocal.app.ui.screens.user.MyPlacesScreen
import com.unilocal.app.ui.screens.user.RegisterPlaceScreen
import com.unilocal.app.ui.screens.user.RegisterScreen
import com.unilocal.app.viewmodel.MainViewModel
@Composable
fun Navigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Login.route
        ) {
            composable(NavRoutes.Login.route) {
                LoginScreen(
                    onNavigateToRegister = { navController.navigate(NavRoutes.Register.route) },
                    onNavigateToHome = { navController.navigate(NavRoutes.Home.route) },
                    onNavigateToAdmin = { navController.navigate(NavRoutes.AdminPanel.route) }
                )
            }

            composable(NavRoutes.Register.route) {
                RegisterScreen(
                    navController = navController,
                    onRegister = { navController.navigate(NavRoutes.Home.route) }
                )
            }

            composable(NavRoutes.Home.route) {
                HomeScreen(navController = navController)
            }

            composable(NavRoutes.RegisterPlace.route) {
                RegisterPlaceScreen(
                    navController = navController,
                    onRegister = { navController.popBackStack() }
                )
            }

            composable(NavRoutes.MyPlaces.route) {
                MyPlacesScreen(navController = navController)
            }

            composable(NavRoutes.EditProfile.route) {
                EditProfileScreen(
                    navController = navController,
                    onSave = { navController.navigate(NavRoutes.Home.route) }
                )
            }

            composable(NavRoutes.AdminPanel.route) {
                AdminPanelScreen(navController)
            }
            composable(NavRoutes.RequestList.route) {
                RequestListScreen(navController)
            }
            composable(NavRoutes.RequestHistory.route) {
                RequestHistoryScreen(navController)
            }

        }
    }
}
