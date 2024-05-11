package com.example.remind.feature.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.remind.app.Screens

@Composable
fun SelectTypeScreen(
    navController: NavHostController
){
    Column() {
        Text("선택유")
        Button(onClick = {
            navController.navigate(Screens.Patience.route)
        }) {
            Text("환자")
        }
        Button(onClick = {
            navController.navigate(Screens.Center.route)
        }) {
            Text("센터")
        }
        Button(onClick = {
            navController.navigate(Screens.Doctor.route)
        }) {
            Text("의사")
        }
    }
}