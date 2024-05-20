package com.example.remind.feature.screens.patience.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeCheeringScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val effectFlow = viewModel.effect
    LaunchedEffect(true) {
        delay(3000)
        effectFlow.collectLatest { effect->
            when(effect) {
                is HomeContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destinaton, effect.navOptions)
                }
                else -> {}
            }
        }
    }
    RemindTheme {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_example_cheer),
            contentDescription = null
        )

    }
}

@Preview
@Composable
fun CheeringPreview() {
}