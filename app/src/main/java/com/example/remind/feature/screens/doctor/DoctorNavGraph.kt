package com.example.remind.feature.screens.doctor

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.remind.app.Screens

fun NavGraphBuilder.DoctorGraph(
    navHostController: NavHostController
) {
    navigation(
        route = Screens.Doctor.route,
        startDestination = Screens.Doctor.DoctorMain.route
    ) {
        composable(route = Screens.Doctor.DoctorMain.route) {
            DoctorMain(navHostController)
        }
        composable(route = Screens.Doctor.DoctorPatienceRegister.route) {
            //나중에 바뀌어야함
            DoctorMain(navHostController)
        }
    }
}