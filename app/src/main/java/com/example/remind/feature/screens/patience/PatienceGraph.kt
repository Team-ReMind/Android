package com.example.remind.feature.screens.patience


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.remind.app.Screens
import androidx.navigation.compose.composable
import com.example.remind.feature.screens.patience.home.HomeScreen
import com.example.remind.feature.screens.patience.writing.WritingMoodStep1Screen
import com.example.remind.feature.screens.patience.writing.WritingMoodStep2Screen
import com.example.remind.feature.screens.patience.writing.WritingMoodStep3Screen

@RequiresApi(Build.VERSION_CODES.O)
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
            HomeScreen(navController)
        }
        composable(route = Screens.Patience.Home.WritingMoodStep1.route) {
            WritingMoodStep1Screen(navController)
        }
        composable(route = Screens.Patience.Home.WritingMoodStep2.route) {
            WritingMoodStep2Screen()
        }
        composable(route = Screens.Patience.Home.WritingMoodStep3.route) {
            WritingMoodStep3Screen()
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