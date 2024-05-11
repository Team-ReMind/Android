package com.example.remind.feature.screens.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavHostController
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is LoginContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destinaton, effect.navOptions)
                }
                else->{}
            }
        }
    }

    Column() {
        Button(onClick = {
            viewModel.setEvent(LoginContract.Event.KakaoLoginButtonClicked(context))
        }) {
            Text(text = "로그인")
        }
    }
}