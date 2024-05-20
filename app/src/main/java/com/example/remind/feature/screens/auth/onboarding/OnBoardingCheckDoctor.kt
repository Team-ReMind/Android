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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicOnBoardingAppBar
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.request.OnBoardingRequest
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingCheckDoctorScreen(navController: NavHostController) {
    val viewModel: OnBoardingViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current
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
            Spacer(modifier = Modifier.weight(1f))
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
                  viewModel.setEvent(
                      OnBoardingContract.Event.NavigateButtonClicked(
                          Screens.Register.OnBoardingLoadingDoctor.route,
                          Screens.Register.OnBoardingCheckDoctor.route
                     )
                  )
                    viewModel.setEvent(OnBoardingContract.Event.NextButtonFinal(
                        OnBoardingRequest(
                            centerName = "",
                            city = "",
                            district = "",
                            protectorPhoneNumber = "01088644622",
                            rolesType = "ROLE_DOCTOR",
                            fcmToken = "",
                            doctorLicenseNumber = ""
                        )
                    ))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }

}