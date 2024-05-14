package com.example.remind.feature.screens.auth.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .width(139.dp)
                            .height(83.dp),
                        painter = painterResource(id = R.drawable.ic_logo_splash),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = stringResource(id = R.string.진료실_밖_일상을_더하다),
                        style = RemindTheme.typography.onSplashFont.copy(color = RemindTheme.colors.white)
                    )
                }
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