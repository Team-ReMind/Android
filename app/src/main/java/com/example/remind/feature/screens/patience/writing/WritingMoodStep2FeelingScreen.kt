package com.example.remind.feature.screens.patience.writing

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.IconContainer
import com.example.remind.core.common.component.RemindTextField
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.FeelingScoreModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WritingMoodStep2FeelingScreen(navController: NavHostController, viewModel: WritingViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current
    val feelingList = listOf(
        FeelingScoreModel(R.drawable.ic_verygood, "정말 좋음", "VERY_GOOD"),
        FeelingScoreModel(R.drawable.ic_good, "좋음", "GOOD"),
        FeelingScoreModel(R.drawable.ic_normal, "보통", "NORMAL"),
        FeelingScoreModel(R.drawable.ic_bad, "나쁨", "BAD"),
        FeelingScoreModel(R.drawable.ic_terrible, "끔찍함", "TERRIBLE")
    )
    val textState = remember { mutableStateOf("") }
    val handleTextChange = { newText: String ->
        textState.value = newText
    }

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is WritingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destinaton, effect.navOptions)
                }
                is WritingContract.Effect.Toastmessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    RemindTheme {
        Column(
            modifier = Modifier
                .background(color = RemindTheme.colors.white)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            IconButton(
                onClick = {
                    viewModel.setEvent(WritingContract.Event.PreviousButtonClicked(
                        Screens.Patience.Home.WritingMoodStep1.route,
                        Screens.Patience.Home.WritingMoodStep2.route
                    ))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrowleft),
                    contentDescription = null,
                    tint = RemindTheme.colors.icon
                )

            }
            StepContainer2(modifier = Modifier.padding(top = 31.dp))
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.오늘_하루_어떤_활동을_하셨나요_),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text, lineHeight = 44.sp)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.오늘의_활동을_선택해주세요),
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_400)
            )
            Text(
                modifier = Modifier.padding(top = 47.dp),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.해당_활동이_어떤_영향을_주었나요),
                style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.slate_600)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                            if(uiState.moodActivity.feelingType == feelingList.get(i).text) RemindTheme.colors.main_4 else RemindTheme.colors.white,
                            onClick = {
                                viewModel.setEvent(WritingContract.Event.FeelingActivityButtonClicked(feelingList.get(i).text))
                            }
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 19.dp),
                text = stringResource(id = R.string.활동을_할_때),
                style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.slate_600)
            )
            Spacer(modifier = Modifier.height(6.dp))
            RemindTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onTextChanged = handleTextChange,
                text = textState.value,
                roundedShape = 18.dp,
                hintText = stringResource(id = R.string.힌트_오랜만에_),
                topPadding = 15.dp,
                bottomPadding = 0.dp
            )
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                text = stringResource(id = R.string.다음),
                RoundedCorner = 12.dp,
                backgroundColor = if(uiState.moodActivity.feelingType != "") RemindTheme.colors.main_6 else RemindTheme.colors.slate_100,
                textColor = if(uiState.moodActivity.feelingType != "") RemindTheme.colors.white else RemindTheme.colors.slate_300,
                verticalPadding = 13.dp,
                onClick = {
                     viewModel.setEvent(WritingContract.Event.StoreFeelingListItem(textState.value))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }
}