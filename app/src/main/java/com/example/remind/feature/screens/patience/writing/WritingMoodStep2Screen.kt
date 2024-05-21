package com.example.remind.feature.screens.patience.writing

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.StepComponent
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WritingMoodStep2Screen(navController: NavHostController, viewModel: WritingViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current

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
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                BasicButton(
                   text = stringResource(id = R.string.활동_편집),
                    RoundedCorner = 20.dp,
                    backgroundColor = RemindTheme.colors.main_6,
                    textColor = RemindTheme.colors.slate_50,
                    verticalPadding = 3.dp,
                    onClick = { },
                    textStyle = RemindTheme.typography.c3Bold
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyHorizontalGrid(
                   rows = GridCells.Fixed(2)
                ) {
                    itemsIndexed(uiState.ActivityList) {index, item ->
                        ImageContainerContent(
                            background = if(uiState.activityId != null) RemindTheme.colors.main_4 else  RemindTheme.colors.white,
                            onClick = {
                                viewModel.setEvent(WritingContract.Event.ActivityButtonClicked(
                                    item.activityId,
                                    item.name
                                ))
                            },
                            imageUrl = item.iconImage,
                            name = item.name
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                text = stringResource(id = R.string.다음),
                RoundedCorner = 12.dp,
                backgroundColor = if(uiState.activityId != null) RemindTheme.colors.main_6 else RemindTheme.colors.slate_100,
                textColor = if(uiState.activityId != null) RemindTheme.colors.white else RemindTheme.colors.slate_300,
                verticalPadding = 13.dp,
                onClick = {
                    viewModel.setEvent(WritingContract.Event.NextButtonClicked(
                        Screens.Patience.Home.WritingMoodStep2Feeling.route,
                        Screens.Patience.Home.WritingMoodStep2.route
                    ))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
         }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageContainerContent(
    modifier: Modifier = Modifier,
    background: Color,
    onClick: () -> Unit,
    imageUrl: String,
    name: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = background, shape = RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick
            )
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                modifier = modifier.padding(bottom = 6.dp),
                model = imageUrl,
                contentDescription = null
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = RemindTheme.typography.c3Bold.copy(color = RemindTheme.colors.slate_700)
            )
        }
    }
}

@Composable
fun StepContainer2(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        StepComponent(text = 1, backgroundColor = RemindTheme.colors.main_6, textColor = RemindTheme.colors.slate_50)
        Spacer(modifier = modifier.width(6.dp))
        StepComponent(text = 2, backgroundColor = RemindTheme.colors.main_6, textColor = RemindTheme.colors.slate_50)
        Spacer(modifier = modifier.width(6.dp))
        StepComponent(text = 3, backgroundColor = RemindTheme.colors.slate_500, textColor = RemindTheme.colors.slate_400)
    }
}
