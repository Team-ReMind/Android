package com.example.remind.feature.screens.auth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = RemindTheme.colors.main_6)
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 73.dp, end = 73.dp),
                    painter = painterResource(id = R.drawable.ic_splash_logo),
                    contentDescription = null,
                )
            }
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