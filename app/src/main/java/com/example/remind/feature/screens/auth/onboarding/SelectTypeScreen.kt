package com.example.remind.feature.screens.auth.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.designsystem.theme.Pretendard
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SelectTypeScreen(
    navController: NavHostController
){
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
            }
        }
    }
    RemindTheme {
        Column() {
            TopBar(modifier = Modifier)
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 31.dp),
                text = stringResource(id = R.string.사용_입장_선택),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text)
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 12.dp),
                text = stringResource(id = R.string.어떤_입장에서_사용하실지_선택해주세요),
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.grayscale_3)
            )
            typeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 60.dp, end = 60.dp, top = 115.dp),
                backgroundColor = if(uiState.selectedType == "Patience") RemindTheme.colors.main_4 else RemindTheme.colors.slate_100,
                text = stringResource(id = R.string.환자용),
                onClick = {
                    viewModel.setEvent(OnBoardingContract.Event.PatienceButtonClicked(context))
                },
                textColor = RemindTheme.colors.slate_700
            )
            typeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 60.dp, end = 60.dp, top = 22.dp),
                backgroundColor = if(uiState.selectedType == "Doctor") RemindTheme.colors.main_4 else RemindTheme.colors.slate_100,
                text = stringResource(id = R.string.의사용),
                onClick = {
                    viewModel.setEvent(OnBoardingContract.Event.DoctorButtonClicked(context))
                },
                textColor = RemindTheme.colors.slate_700
            )
            typeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 60.dp, end = 60.dp, top = 22.dp),
                backgroundColor = if(uiState.selectedType == "Center") RemindTheme.colors.main_4 else RemindTheme.colors.slate_100,
                text = stringResource(id = R.string.센터용),
                onClick = {
                    viewModel.setEvent(OnBoardingContract.Event.CenterButtonClicked(context))
                },
                textColor = RemindTheme.colors.slate_700
            )
            Spacer(modifier = Modifier.weight(1f))
            typeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 32.dp),
                backgroundColor = if(uiState.selectedType == null) RemindTheme.colors.slate_100 else  RemindTheme.colors.main_6,
                text = stringResource(id = R.string.다음),
                onClick = {
                    viewModel.setEvent(OnBoardingContract.Event.NextButtonClicked(context))
                },
                textColor = if(uiState.selectedType == null) RemindTheme.colors.slate_300 else  RemindTheme.colors.white
            )
        }
    }
}

@Composable
fun TopBar(
    modifier : Modifier = Modifier,
 ) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = modifier.padding(top = 20.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.환자_관리),
            style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.text)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .weight(0.3f)
                    .height(4.dp)
                    .padding(start = 0.dp)
                    .clip(shape = RoundedCornerShape(23.dp))
                    .background(color = RemindTheme.colors.main_6)
            )
            Spacer(modifier = modifier.weight(0.7f))
        }
    }
}

@Composable
fun typeButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    text: String,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(color = backgroundColor)
            .clickable(
                enabled = backgroundColor != RemindTheme.colors.slate_100,
                onClick = onClick
            )
    ) {
        Text(
            modifier = modifier
                .padding(vertical = 12.dp),
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                lineHeight = 8.sp,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                ),
                color = textColor
            ),
        )
    }
}

@Preview(showBackground = false)
@Composable
fun SelectTypePreview() {
    typeButton(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = RemindTheme.colors.main_4,
        text = stringResource(id = R.string.센터용),
        onClick = {},
        textColor = RemindTheme.colors.text
    )
}