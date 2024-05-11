package com.example.remind.feature.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.remind.app.Screens
import com.example.remind.feature.screens.auth.onboarding.OnBoardingContract
import com.example.remind.feature.screens.auth.onboarding.OnBoardingViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SelectTypeScreen(
    navController: NavHostController
){
    val viewModel: OnBoardingViewModel = hiltViewModel()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is OnBoardingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
            }
        }
    }

    Column() {
        Text("선택유")
        Button(onClick = {
            viewModel.setEvent(OnBoardingContract.Event.PatienceButtonClicked(context))
        }) {
            Text("환자")
        }
        Button(onClick = {
            viewModel.setEvent(OnBoardingContract.Event.CenterButtonClicked(context))
        }) {
            Text("센터")
        }
        Button(onClick = {
            viewModel.setEvent(OnBoardingContract.Event.DoctorButtonClicked(context))
        }) {
            Text("의사")
        }
    }
}