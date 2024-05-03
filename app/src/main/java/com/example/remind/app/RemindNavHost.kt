package com.example.remind.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.remind.feature.screens.patience.FirstScreen
import com.example.remind.feature.screens.patience.FourthScreen
import com.example.remind.feature.screens.patience.PatienceScreen
import com.example.remind.feature.screens.patience.SecondScreen
import com.example.remind.feature.screens.patience.ThirdScreen
import com.example.remind.feature.screens.register.RegisterGraph

@Composable
fun RemindNavHost() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Screens.Register.route
    ) {
        RegisterGraph(navHostController)


        composable(route = Screens.Patience.route) {
            PatienceScreen()
        }

    }
}

