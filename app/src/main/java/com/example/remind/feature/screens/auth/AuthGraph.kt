package com.example.remind.feature.screens.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.remind.app.Screens
import com.example.remind.feature.screens.auth.login.LoginViewModel

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.remind.feature.screens.auth.login.LoginScreen
import com.example.remind.feature.screens.auth.onboarding.SelectTypeScreen


fun NavGraphBuilder.RegisterGraph(
    navHostController: NavHostController
) {
    navigation(
        route = Screens.Register.route,
        startDestination = Screens.Register.Login.route
    ) {
        composable(route = Screens.Register.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(viewModel,navHostController)
        }
        composable(route = Screens.Register.SelectType.route) {
            SelectTypeScreen(navHostController)
        }
    }
}