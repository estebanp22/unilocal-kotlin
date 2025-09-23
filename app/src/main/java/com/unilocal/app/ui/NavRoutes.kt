package com.unilocal.app.ui

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Register : NavRoutes("register")
    object Home : NavRoutes("home")
    object RegisterPlace : NavRoutes("register_place")

    object EditProfile : NavRoutes("editProfile")

}
