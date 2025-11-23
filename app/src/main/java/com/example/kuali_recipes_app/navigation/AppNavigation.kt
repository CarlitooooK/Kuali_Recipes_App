package com.example.kuali_recipes_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kuali_recipes_app.presentation.LoginScreen
import com.example.kuali_recipes_app.presentation.RegisterScreen
import com.example.kuali_recipes_app.presentation.HomeScreen
import com.example.kuali_recipes_app.presentation.RioBottomNav


@Composable
fun AppNavigation() {
    val navCotroller = rememberNavController()
    NavHost(navCotroller,AppScreens.LoginScreen.route) {
        composable(AppScreens.LoginScreen.route) {
            LoginScreen(
                onLoginSuccess = {
                    navCotroller.navigate(AppScreens.RioBottomNav.route)
                },
                onNavigatetoRegister = {
                    navCotroller.navigate(AppScreens.RegisterScreen.route)
                }
            )
        }
        composable(AppScreens.RegisterScreen.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navCotroller.navigate(AppScreens.RioBottomNav.route)
                },
                onNavigatetoLogin = {
                    navCotroller.navigate(AppScreens.LoginScreen.route)
                }
            )

        }
        composable(AppScreens.HomeScreen.route) {
            HomeScreen()
        }
        composable(AppScreens.RioBottomNav.route){
            RioBottomNav()
        }
    }

}