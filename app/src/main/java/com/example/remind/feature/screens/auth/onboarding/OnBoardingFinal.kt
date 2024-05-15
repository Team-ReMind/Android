package com.example.remind.feature.screens.auth.onboarding

import android.graphics.Paint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingFinalScreen(
    navController: NavHostController
) {
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
                is OnBoardingContract.Effect.Toastmessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    RemindTheme {
        PatienceFinal(
            name = "배예진",
            onClick = {
                viewModel.setEvent(OnBoardingContract.Event.NextButtonToPatience(context))
            }
        )
    }
}

@Composable
fun PatienceFinal(
    modifier: Modifier = Modifier,
    name: String,
    onClick: () -> Unit
) {
    var title = stringResource(id = R.string.반가워요_환자님, name)
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
                text = stringResource(id = R.string.오늘_하루의_통합적_기분을_선택해주세요),
                style = RemindTheme.typography.b1Medium.copy(color = RemindTheme.colors.white)
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
@Preview
@Composable
fun FinalPreview() {
    PatienceFinal(
        name = "배예진",
        onClick = {}
    )
}