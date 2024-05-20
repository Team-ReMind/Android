package com.example.remind.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.remind.feature.screens.center.CenterGraph
import com.example.remind.feature.screens.doctor.DoctorGraph
import com.example.remind.feature.screens.patience.PatienceScreen
import com.example.remind.feature.screens.auth.RegisterGraph
import com.example.remind.feature.screens.auth.onboarding.OnBoardingViewModel
import com.example.remind.feature.screens.auth.splash.SplashGraph

@Composable
fun RemindNavHost() {
    val navHostController = rememberNavController()
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navHostController,
        startDestination = Screens.Splash.route
    ) {
        SplashGraph(navHostController)
        RegisterGraph(navHostController, onBoardingViewModel)
        composable(route = Screens.Patience.route) {
            PatienceScreen()
        }
        DoctorGraph(navHostController)
        CenterGraph(navHostController)
    }
}

