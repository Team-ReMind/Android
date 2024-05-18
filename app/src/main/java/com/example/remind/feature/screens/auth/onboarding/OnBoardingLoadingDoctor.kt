package com.example.remind.feature.screens.auth.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingLoadingDoctorScreen(navController: NavHostController) {
    val viewModel: OnBoardingViewModel = hiltViewModel()
    val effectFlow = viewModel.effect
    LaunchedEffect(true) {
        delay(3000)
        viewModel.navigateToFinal()
        effectFlow.collectLatest { effect->
            when(effect) {
                is OnBoardingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
                else -> {}
            }
        }
    }
    RemindTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.img_doctor_loading),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 95.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = stringResource(id = R.string.만나서_반갑습니다_),
                    style = RemindTheme.typography.h1Bold.copy(
                        color = RemindTheme.colors.white,
                        lineHeight = 40.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.의사_면허_인증_중),
                    style = RemindTheme.typography.b3Regular.copy(
                        color = RemindTheme.colors.white,
                        lineHeight = 28.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    RemindTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.img_doctor_loading),
                contentScale = ContentScale.FillBounds,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 20.dp, top = 95.dp)
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = stringResource(id = R.string.만나서_반갑습니다_),
                    style = RemindTheme.typography.h1Bold.copy(
                        color = RemindTheme.colors.white,
                        lineHeight = 40.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.의사_면허_인증_중),
                    style = RemindTheme.typography.b3Regular.copy(
                        color = RemindTheme.colors.white,
                        lineHeight = 28.sp
                    )
                )
            }
        }
    }
}