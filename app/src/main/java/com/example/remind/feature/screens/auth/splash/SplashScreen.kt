package com.example.remind.feature.screens.auth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val viewModel: SplashViewModel = hiltViewModel()
    val effectFlow = viewModel.effect

    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(painter = painterResource(
                id = R.drawable.ic_logo),
                contentDescription = null)
        }
    }

    LaunchedEffect(true) {
        delay(3000)
        viewModel.checkUserState()
        effectFlow.collectLatest { effect->
            when(effect) {
                is SplashContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
            }
        }
    }
}