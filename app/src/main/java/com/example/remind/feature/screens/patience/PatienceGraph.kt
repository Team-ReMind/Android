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
        startDestination = Screens.Patience.Home.route
    ) {
        composable(route = Screens.Patience.Home.route) {
            HomeScreen()
        }
        composable(route = Screens.Patience.MoodChart.route) {
            MoodChartScreen()
        }
        composable(route = Screens.Patience.Medicine.route) {
            MedicineScreen()
        }
        composable(route = Screens.Patience.MyPage.route) {
            MyPageScreen()
        }
    }
}