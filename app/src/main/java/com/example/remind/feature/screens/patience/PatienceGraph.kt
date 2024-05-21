package com.example.remind.feature.screens.patience


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.example.remind.app.Screens
import androidx.navigation.compose.composable
import com.example.remind.feature.screens.patience.home.HomeCheeringScreen
import com.example.remind.feature.screens.patience.home.HomeScreen
import com.example.remind.feature.screens.patience.home.HomeViewModel
import com.example.remind.feature.screens.patience.medicine.MedicineScreen
import com.example.remind.feature.screens.patience.moodchart.MoodChartScreen
import com.example.remind.feature.screens.patience.moodchart.MoodChartViewModel
import com.example.remind.feature.screens.patience.writing.WritingMoodStep1Screen
import com.example.remind.feature.screens.patience.writing.WritingMoodStep2FeelingScreen
import com.example.remind.feature.screens.patience.writing.WritingMoodStep2LastScreen
import com.example.remind.feature.screens.patience.writing.WritingMoodStep2Screen
import com.example.remind.feature.screens.patience.writing.WritingMoodStep3Screen
import com.example.remind.feature.screens.patience.writing.WritingViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PatienceGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val HomeviewModel: HomeViewModel = hiltViewModel()
    val WritingviewModel: WritingViewModel = hiltViewModel()
    val MoodChartViewModel: MoodChartViewModel  = hiltViewModel()
    NavHost(
        navController = navController,
        route = Screens.Patience.route,
        startDestination = Screens.Patience.Home.route
    ) {
        composable(route = Screens.Patience.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.Patience.Home.WritingMoodStep1.route) {
            WritingMoodStep1Screen(navController, WritingviewModel)
        }
        composable(route = Screens.Patience.Home.WritingMoodStep2.route) {
            WritingMoodStep2Screen(navController, WritingviewModel)
        }
        composable(route = Screens.Patience.Home.WritingMoodStep2Feeling.route) {
            WritingMoodStep2FeelingScreen(navController, WritingviewModel)
        }
        composable(route = Screens.Patience.Home.WritingMoodStep2Last.route) {
            WritingMoodStep2LastScreen(navController, WritingviewModel)
        }
        composable(route = Screens.Patience.Home.WritingMoodStep3.route) {
            WritingMoodStep3Screen(navController, WritingviewModel)
        }
        composable(route = Screens.Patience.Home.SplashCheering.route) {
            HomeCheeringScreen(navController)
        }
        composable(route = Screens.Patience.MoodChart.route) {
            MoodChartScreen(navController, MoodChartViewModel)
        }
        composable(route = Screens.Patience.Medicine.route) {
            MedicineScreen()
        }
        composable(route = Screens.Patience.MyPage.route) {
            MyPageScreen()
        }
    }
}