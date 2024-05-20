package com.example.remind.feature.screens.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.remind.app.Screens
import com.example.remind.feature.screens.auth.login.LoginViewModel

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remind.feature.screens.auth.login.LoginScreen
import com.example.remind.feature.screens.auth.onboarding.OnBoardingCenterScreen
import com.example.remind.feature.screens.auth.onboarding.OnBoardingCheckDoctorScreen
import com.example.remind.feature.screens.auth.onboarding.OnBoardingFinalScreen
import com.example.remind.feature.screens.auth.onboarding.OnBoardingLoadingDoctorScreen
import com.example.remind.feature.screens.auth.onboarding.OnBoardingPatienceScreen
import com.example.remind.feature.screens.auth.onboarding.OnBoardingViewModel
import com.example.remind.feature.screens.auth.onboarding.SelectTypeScreen
import com.example.remind.feature.screens.patience.home.HomeViewModel

fun NavGraphBuilder.RegisterGraph(
    navHostController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel
) {
    navigation(
        route = Screens.Register.route,
        startDestination = Screens.Register.Login.route
    ) {
        composable(route = Screens.Register.Login.route) {
            LoginScreen(navHostController)
        }
        composable(route = Screens.Register.SelectType.route) {
            SelectTypeScreen(navHostController, onBoardingViewModel)
        }
        composable(route = Screens.Register.OnBoardingPatience.route) {
            OnBoardingPatienceScreen(navHostController, onBoardingViewModel)
        }
        composable(route = Screens.Register.OnBoardingCheckDoctor.route) {
            OnBoardingCheckDoctorScreen(navHostController)
        }
        composable(route = Screens.Register.OnBoardingLoadingDoctor.route) {
            OnBoardingLoadingDoctorScreen(navHostController)
        }
        composable(route = Screens.Register.OnBoardingCenter.route) {
            OnBoardingCenterScreen()
        }
        composable(route = Screens.Register.OnBoardingFinal.route) {
            OnBoardingFinalScreen(navHostController,onBoardingViewModel)
        }
    }
}