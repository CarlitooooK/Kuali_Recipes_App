package com.example.kuali_recipes_app.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("login_screen")
    object RegisterScreen: AppScreens("register_screen")
    object HomeScreen: AppScreens("home_screen")
    object RioBottomNav: AppScreens("rio_bottom")


}