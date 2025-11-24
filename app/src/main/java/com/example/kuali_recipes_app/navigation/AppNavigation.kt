package com.example.kuali_recipes_app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kuali_recipes_app.presentation.auth.LoginScreen
import com.example.kuali_recipes_app.presentation.auth.RegisterScreen
import com.example.kuali_recipes_app.presentation.HomeScreen
import com.example.kuali_recipes_app.presentation.RioBottomNav


@Composable
fun AppNavigation() {
    val navCotroller = rememberNavController()
    NavHost(
        navController = navCotroller,
        startDestination = AppScreens.LoginScreen.route,
        // Animación global para todas las pantallas (o hazlo individualmente en cada composable)
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(800) // Duración en ms
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(800)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }
    ){
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