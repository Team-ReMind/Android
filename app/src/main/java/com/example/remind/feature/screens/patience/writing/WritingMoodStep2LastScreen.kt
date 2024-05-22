package com.example.remind.feature.screens.patience.writing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.FeelingScoreModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun WritingMoodStep2LastScreen(navController: NavHostController, viewModel: WritingViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val feelingList = listOf(
        FeelingScoreModel(R.drawable.ic_verygood, "정말 좋음", "VERY_GOOD", "기분이 정말 좋았어요!"),
        FeelingScoreModel(R.drawable.ic_good, "좋음", "GOOD", "기분이 좋았어요!"),
        FeelingScoreModel(R.drawable.ic_normal, "보통", "NORMAL", "기분이 보통이었어요!"),
        FeelingScoreModel(R.drawable.ic_bad, "나쁨", "BAD", "기분이 나빴어요."),
        FeelingScoreModel(R.drawable.ic_terrible, "끔찍함", "TERRIBLE", "기분이 매우 안좋았어요.")
    )
    fun findActivityImage(activityId: Int): String? {
        val moodActivity = uiState.ActivityList.find { it.activityId ==  activityId}
        return moodActivity?.iconImage
    }
    fun findActivityName(activityId: Int): String? {
        val moodActivity = uiState.ActivityList.find { it.activityId ==  activityId}
        return moodActivity?.name
    }
    fun findMoodImage(feelingType: String): Int? {
        val moodFeeling = feelingList.find { it.text ==  feelingType}
        return moodFeeling?.imgeRes
    }
    fun findMoodDescription(feelingType: String): String? {
        val moodFeeling = feelingList.find { it.text ==  feelingType}
        return moodFeeling?.description
    }
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

    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = RemindTheme.colors.white)
                .padding(horizontal = 20.dp)
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
            StepContainer2(
                modifier = Modifier.padding(top = 31.dp)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.오늘_하루_어떤_활동을_하셨나요_),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text, lineHeight = 44.sp)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.오늘의_활동은_최대_3개까지),
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_400)
            )
            if(uiState.writingMoodRequest.moodActivities.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.padding(top = 30.dp),
                ) {
                    itemsIndexed(uiState.writingMoodRequest.moodActivities) {index, item ->
                        ActivityListContainer(
                            modifier = Modifier.padding(bottom = 10.dp),
                            activityImage = findActivityImage(item.activityId)!!,
                            activityText = findActivityName(item.activityId)!!,
                            moodImage = findMoodImage(item.feelingType)!!,
                            moodDescription = findMoodDescription(item.feelingType)!!,
                            detail = item.detail
                        )
                    }
                }
            }
            PlusContainer(
                modifier = Modifier.padding(top = 10.dp),
                onClick = {
                    viewModel.setEvent(WritingContract.Event.NavigateToStep2(
                        Screens.Patience.Home.WritingMoodStep2.route,
                        Screens.Patience.Home.WritingMoodStep2Last.route
                    ))
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                text = stringResource(id = R.string.다음),
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 13.dp,
                onClick = {
                   viewModel.setEvent(WritingContract.Event.NextButtonClicked(
                       Screens.Patience.Home.WritingMoodStep3.route,
                       Screens.Patience.Home.WritingMoodStep2Last.route
                   ))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ActivityListContainer(
    modifier: Modifier = Modifier,
    activityImage: String,
    activityText: String,
    moodImage: Int,
    moodDescription: String,
    detail: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = RemindTheme.colors.grayscale_1,
                shape = RoundedCornerShape(8.dp)
            )
            .background(color = RemindTheme.colors.white, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
        ) {
            Column(
                modifier = Modifier.padding(top = 6.dp, bottom = 13.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    modifier = Modifier.padding(bottom = 6.dp),
                    model = activityImage,
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .background(
                            color = RemindTheme.colors.main_2,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 2.5.dp),
                    text = activityText,
                    style = RemindTheme.typography.c2Medium.copy(color = RemindTheme.colors.text)
                )
            }
            Column(
                modifier = Modifier.padding(start= 43.dp, top = 6.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.size(width = 15.dp, height = 21.dp),
                        painter = painterResource(id = moodImage),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 3.dp, bottom = 6.dp),
                        text = moodDescription,
                        style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_600)
                    )
                }
                Text(
                    text = detail,
                    style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_500)
                )
            }
        }

    }
}
@Composable
fun PlusContainer(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = RemindTheme.colors.white, shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = RemindTheme.colors.grayscale_1,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null
            )
            Text(
                modifier = modifier.padding(top = 6.dp),
                text = stringResource(id = R.string.활동_추가하기),
                style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.main_6)
            )
        }
    }
}
