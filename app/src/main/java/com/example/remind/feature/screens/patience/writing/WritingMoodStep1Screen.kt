package com.example.remind.feature.screens.patience.writing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.StepComponent
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.FeelingScoreModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WritingMoodStep1Screen(
    navController: NavHostController
){
    val viewModel: WritingViewModel = hiltViewModel()
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
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp
            )
        ) {
            IconButton(
                onClick = {
                    viewModel.navigateToRoute(
                        destination = Screens.Patience.Home.route,
                        current = Screens.WritingMood.WritingMoodStep1.route
                    )
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrowleft),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 9.dp, height = 16.dp)
                        .padding(top = 24.dp, start = 6.dp),
                    tint = RemindTheme.colors.icon
                )

            }
            StepContainer(
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.오늘_하루_어떤_기분을_느끼셨나요_),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.오늘_하루의_전반적인_기분을_선택해주세요),
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_400)
            )
            Spacer(modifier = Modifier.weight(1f))
            //이모티콘 박스
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        shape = RoundedCornerShape(18.dp),
                        color = RemindTheme.colors.grayscale_1
                    )
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for(i in 0..4) {
                        IconContainer(
                            feelingScoreModel = feelingList.get(i),
                            backgroundColor =
                            if(uiState.selectFeelingType == feelingList.get(i).text) RemindTheme.colors.main_4 else RemindTheme.colors.white,
                            onClick = {
                                viewModel.setEvent(WritingContract.Event.FeelingButtonClicked(feelingList.get(i).text))
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                text = stringResource(id = R.string.다음),
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 13.dp,
                onClick = {
                          viewModel.navigateToRoute(
                             destination = Screens.WritingMood.WritingMoodStep2.route,
                              current = Screens.WritingMood.WritingMoodStep1.route
                          )
                },
                textStyle = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.white)
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


//아이콘 컨테이너 만듬
@Composable
fun IconContainer(
    modifier: Modifier = Modifier,
    feelingScoreModel: FeelingScoreModel,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onClick),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = modifier.padding(
                    top = 18.dp,
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 16.dp
                ),
                painter = painterResource(id = feelingScoreModel.imgeRes),
                contentDescription = null
            )
            Text(
                modifier = modifier.padding(bottom = 15.dp),
                text = feelingScoreModel.text,
                style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.slate_700)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WritingPreview() {
}