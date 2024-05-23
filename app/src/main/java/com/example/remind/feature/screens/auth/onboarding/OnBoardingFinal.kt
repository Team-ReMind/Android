package com.example.remind.feature.screens.auth.onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingFinalScreen(
    navController: NavHostController,
    viewModel: OnBoardingViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is OnBoardingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
                is OnBoardingContract.Effect.Toastmessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    RemindTheme {
        when(uiState.selectedType) {
            "ROLE_PATIENT" -> {
            PatienceFinal(
                name = uiState.userName,
                onClick = {
                    viewModel.setEvent(OnBoardingContract.Event.NavigateButtonClicked(
                        Screens.Patience.route,
                        Screens.Register.OnBoardingFinal.route,
                        true
                    ))
                }
            )
          }
            "ROLE_DOCTOR" -> {
                DoctorFinal(
                    name = uiState.userName,
                    onClick = {
                        viewModel.setEvent(OnBoardingContract.Event.NavigateButtonClicked(
                            Screens.Doctor.DoctorMain.route,
                            Screens.Register.OnBoardingFinal.route,
                            true
                        ))
                    }
                )
            }
            "ROLE_CENTER" -> {
                CenterFinal(
                    onClick = {
                        viewModel.setEvent(OnBoardingContract.Event.NavigateButtonClicked(
                            Screens.Center.CenterMain.route,
                            Screens.Register.OnBoardingFinal.route,
                            true
                        ))
                    },
                )
            }
        }
    }
}

@Composable
fun PatienceFinal(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit
) {
    var title = "반가워요!"
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            painter = painterResource(R.drawable.img_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = modifier.align(Alignment.TopStart)
        ) {
            Text(
                modifier = modifier
                    .padding(top = 95.dp, start = 20.dp, end = 20.dp, bottom = 12.dp),
                text = title,
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.white)
            )
            Text(
                modifier = modifier
                    .padding(start = 20.dp, end = 20.dp),
                text = "건강한 회복의 여정을 함께할게요.\n그럼 시작해볼까요?",
                style = RemindTheme.typography.b1Medium.copy(color = RemindTheme.colors.white, lineHeight = 24.sp)
            )
        }
        Image(
            modifier = modifier
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_patience_onboarding),
            contentDescription = null
        )
        BasicButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 32.dp)
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.시작하기),
            RoundedCorner = 12.dp,
            backgroundColor = RemindTheme.colors.main_6,
            textColor = RemindTheme.colors.white,
            verticalPadding = 13.dp,
            onClick = onClick,
            textStyle = RemindTheme.typography.b2Bold
        )
    }
}

@Composable
fun DoctorFinal(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    name: String
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image (
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.img_doctor_final),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Column(
            modifier = modifier
                .align(Alignment.TopStart)
                .padding(top = 90.dp, start = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.만나서_반갑습니다_, name),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.white, lineHeight = 40.sp)
            )
            Text(
                text = stringResource(id = R.string.회원가입이_완료되었습니다),
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.white)
            )
        }
        BasicButton(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 32.dp),
            text = stringResource(id = R.string.시작하기),
            RoundedCorner = 12.dp,
            backgroundColor = RemindTheme.colors.main_6,
            textColor = RemindTheme.colors.white,
            verticalPadding = 13.dp,
            onClick = onClick,
            textStyle = RemindTheme.typography.b2Bold
        )
    }
}

@Composable
fun CenterFinal(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    //name: String
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image (
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.img_center_final),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
        Column(
            modifier = modifier
                .align(Alignment.TopStart)
                .padding(top = 90.dp, start = 20.dp)
        ) {
            Text(
                text = "만나서 반갑습니다.",
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.white, lineHeight = 40.sp)
            )
            Text(
                modifier = modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.회원가입이_완료되었습니다),
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.main_1)
            )
        }
        BasicButton(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 20.dp, end = 20.dp, bottom = 32.dp),
            text = stringResource(id = R.string.시작하기),
            RoundedCorner = 12.dp,
            backgroundColor = RemindTheme.colors.main_6,
            textColor = RemindTheme.colors.white,
            verticalPadding = 13.dp,
            onClick = onClick,
            textStyle = RemindTheme.typography.b2Bold
        )
    }
}


@Preview
@Composable
fun FinalPreview() {
    CenterFinal(
        onClick = {

        },
    )
}