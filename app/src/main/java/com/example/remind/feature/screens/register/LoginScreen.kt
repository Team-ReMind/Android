package com.example.remind.feature.screens.register

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.remind.app.Screens


@Composable
fun LoginScreen(navController: NavHostController){
    Column() {
        Button(onClick = { navController.navigate(Screens.Register.SelectType.route) }) {
            Text(text = "환자버전")
        }
    }
}