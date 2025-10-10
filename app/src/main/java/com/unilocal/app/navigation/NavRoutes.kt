package com.unilocal.app.navigation

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Register : NavRoutes("register")
    object Home : NavRoutes("home")
    object RegisterPlace : NavRoutes("register_place")

    object EditProfile : NavRoutes("editProfile")
    object MyPlaces : NavRoutes("myPlaces")

}