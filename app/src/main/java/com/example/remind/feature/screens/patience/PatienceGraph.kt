package com.example.remind.feature.screens.patience


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.remind.app.Screens
import androidx.navigation.compose.composable
@Composable
fun PatienceGraph(
    navController: NavHostController,
    modifier : Modifier
) {
    NavHost(
        navController = navController,
        route = Screens.Patience.route,
        startDestination = Screens.Patience.Fitst.route
    ) {
        composable(route = Screens.Patience.Fitst.route) {
            FirstScreen()
        }
        composable(route = Screens.Patience.Second.route) {
            SecondScreen()
        }
        composable(route = Screens.Patience.Third.route) {
            ThirdScreen()
        }
        composable(route = Screens.Patience.Fourth.route) {
            FourthScreen()
        }
    }
}