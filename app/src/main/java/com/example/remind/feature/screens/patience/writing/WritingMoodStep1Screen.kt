package com.example.remind.feature.screens.patience.writing

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.example.remind.core.common.component.IconContainer
import com.example.remind.core.common.component.StepComponent
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.FeelingScoreModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WritingMoodStep1Screen(navController: NavHostController, viewModel: WritingViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val feelingList = listOf(
        FeelingScoreModel(R.drawable.ic_verygood, "정말 좋음", "VERY_GOOD"),
        FeelingScoreModel(R.drawable.ic_good, "좋음", "GOOD"),
        FeelingScoreModel(R.drawable.ic_normal, "보통", "NORMAL"),
        FeelingScoreModel(R.drawable.ic_bad, "나쁨", "BAD"),
        FeelingScoreModel(R.drawable.ic_terrible, "끔찍함", "TERRIBLE")
    )
    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is WritingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destinaton, effect.navOptions)
                }
                else -> {}
            }
        }
    }
    RemindTheme{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = RemindTheme.colors.white)
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            IconButton(
                onClick = {
                    viewModel.setEvent(WritingContract.Event.PreviousButtonClicked(
                        Screens.Patience.Home.route,
                        Screens.Patience.Home.WritingMoodStep1.route
                    ))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrowleft),
                    contentDescription = null,
                    tint = RemindTheme.colors.icon
                )

            }
            StepContainer(
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.오늘_하루_어떤_기분을_느끼셨나요_),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text, lineHeight = 44.sp)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.오늘_하루의_전반적인_기분을_선택해주세요),
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_400)
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .background(color = RemindTheme.colors.white)
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(18.dp),
                        color = RemindTheme.colors.grayscale_1
                    )
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 15.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for(i in 0..4) {
                        IconContainer(
                            feelingScoreModel = feelingList.get(i),
                            backgroundColor =
                            if(uiState.writingMoodRequest.feelingType == feelingList.get(i).text) RemindTheme.colors.main_4 else RemindTheme.colors.white,
                            onClick = {
                                viewModel.setEvent(WritingContract.Event.FeelingButtonClicked(feelingList.get(i).text))
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 32.dp
                    ),
                text = stringResource(id = R.string.다음),
                RoundedCorner = 12.dp,
                backgroundColor = if(uiState.writingMoodRequest.feelingType != "") RemindTheme.colors.main_6 else RemindTheme.colors.slate_100,
                textColor = if(uiState.writingMoodRequest.feelingType != "") RemindTheme.colors.white else RemindTheme.colors.slate_300,
                verticalPadding = 13.dp,
                onClick = {
                    viewModel.setEvent(WritingContract.Event.NextButtonClicked(
                        Screens.Patience.Home.WritingMoodStep2.route,
                        Screens.Patience.Home.WritingMoodStep1.route
                    ))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }
}

@Composable
fun StepContainer(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        StepComponent(text = 1, backgroundColor = RemindTheme.colors.main_6, textColor = RemindTheme.colors.slate_50)
        Spacer(modifier = modifier.width(6.dp))
        StepComponent(text = 2, backgroundColor = RemindTheme.colors.slate_500, textColor = RemindTheme.colors.slate_400)
        Spacer(modifier = modifier.width(6.dp))
        StepComponent(text = 3, backgroundColor = RemindTheme.colors.slate_500, textColor = RemindTheme.colors.slate_400)
    }
}

@Preview(showBackground = true)
@Composable
fun WritingPreview() {
    Icon(
        modifier = Modifier
            .size(width = 9.dp, height = 16.dp)
            .padding(top = 24.dp, start = 6.dp),
        painter = painterResource(id = R.drawable.ic_arrowleft),
        contentDescription = null,
        tint = RemindTheme.colors.icon
    )
}