package com.example.remind.feature.screens.auth.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.remind.app.Screens

fun NavGraphBuilder.SplashGraph(
    navHostController: NavHostController
) {
    navigation(
        route = Screens.Splash.route,
        startDestination = Screens.Splash.SplashScreen.route
    ) {
        composable(route = Screens.Splash.SplashScreen.route) {
            SplashScreen(navHostController)
        }
    }
}