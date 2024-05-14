package com.example.remind.feature.screens.auth.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LoginScreen(
    navController: NavHostController
){
    val viewModel: LoginViewModel = hiltViewModel()
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

    RemindTheme {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.img_background),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 22.dp, top = 117.dp),
                    text = stringResource(id = R.string.스스로를_돌아볼_수_있는),
                    style = RemindTheme.typography.onBoardingFont.copy(color = RemindTheme.colors.white)
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 338.dp),
                    painter = painterResource(R.drawable.ic_loginimg),
                    contentDescription = null
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp, start = 20.dp, end = 20.dp),
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(vertical = 13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF9E217),
                        contentColor =Color(0xFF13151B)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.카카오로_로그인하기),
                        style = RemindTheme.typography.b2Bold.copy(color = Color(0xFF13151B))
                    )
                }
            }
        }
    }
}



@Preview
@Composable
fun LoginScreenPreview() {
    RemindTheme {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(R.drawable.img_background),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 22.dp, top = 117.dp),
                    text = stringResource(id = R.string.스스로를_돌아볼_수_있는),
                    style = RemindTheme.typography.onBoardingFont.copy(color = RemindTheme.colors.white)
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 338.dp),
                    painter = painterResource(R.drawable.ic_loginimg),
                    contentDescription = null
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 32.dp, start = 20.dp, end = 20.dp),
                    onClick = {},
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(vertical = 13.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF9E217),
                        contentColor =Color(0xFF13151B)
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.카카오로_로그인하기),
                        style = RemindTheme.typography.b2Bold.copy(color = Color(0xFF13151B))
                    )
                }
            }
        }
    }
}
