package com.unilocal.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unilocal.app.ui.screens.*
import com.unilocal.app.viewmodel.LocalMainViewModel
import com.unilocal.app.viewmodel.MainViewModel

@Composable
fun Navigation(mainViewModel: MainViewModel) {

    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {

        CompositionLocalProvider(LocalMainViewModel provides mainViewModel) {

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
                    MyPlacesScreen(
                        userId = "3", // temporal, luego tomamos del ViewModel
                        onEditPlace = { placeId ->
                            navController.navigate("edit_place/$placeId")
                        },
                        onDeletePlace = { /* delete logic */ },
                        onBack = { navController.popBackStack() }
                    )
                }

                composable(NavRoutes.EditProfile.route) {
                    EditProfileScreen(
                        navController = navController,
                        onSave = { navController.navigate(NavRoutes.Home.route) }
                    )
                }
            }
        }
    }
}
