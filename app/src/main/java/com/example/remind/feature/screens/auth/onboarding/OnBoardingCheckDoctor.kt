package com.example.remind.feature.screens.auth.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicOnBoardingAppBar
import com.example.remind.core.common.component.RemindTextField
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.request.OnBoardingRequest
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingCheckDoctorScreen(
    navController: NavHostController,
    viewModel: OnBoardingViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current
    val textState = remember { mutableStateOf("") }
    val handleTextChange = { newText: String ->
        textState.value = newText
    }
    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is OnBoardingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
                else -> {}
            }
        }
    }
    RemindTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BasicOnBoardingAppBar(
                modifier = Modifier.fillMaxWidth(),
                weight = 0.7f,
                title = stringResource(id = R.string.의사_면허_인증)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.정신질환_환자_사례관리),
                style = RemindTheme.typography.b3Medium.copy(
                    color = RemindTheme.colors.grayscale_3,
                    lineHeight = 49.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 66.dp),
                text= "면허번호",
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
            )
            RemindTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 4.dp),
                onTextChanged = handleTextChange,
                text = textState.value,
                roundedShape = 8.dp,
                hintText = "번호를 입력해주세요.",
                topPadding = 13.dp,
                bottomPadding = 13.dp,
                maxLine = 1
            )
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                text = stringResource(id = R.string.인증_신청),
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_4,
                textColor = RemindTheme.colors.white,
                verticalPadding = 13.dp,
                onClick = {
                  viewModel.setEvent(OnBoardingContract.Event.NextButtonFinalDoctor(
                      certifinumber = textState.value
                  ))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }

}

@Preview
@Composable
fun DoctorPreview() {
    Text(
        text = stringResource(id = R.string.정신질환_환자_사례관리),
        style = RemindTheme.typography.b3Medium.copy(
            color = RemindTheme.colors.grayscale_3,
            lineHeight = 49.sp
        )
    )
}