package com.example.remind.feature.screens.center

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.remind.app.Screens

fun NavGraphBuilder.CenterGraph (
    navHostController: NavHostController
) {
    navigation(
        route = Screens.Center.route,
        startDestination = Screens.Center.CenterMain.route
    ) {
        composable(route = Screens.Center.CenterMain.route) {
            CenterMainScreen()
        }
        composable(route = Screens.Center.CenterPatienceRegister.route) {
            //나중에 바꿔야함
            CenterMainScreen()
        }
    }
}