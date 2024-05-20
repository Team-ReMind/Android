package com.example.remind.feature.screens.patience.writing

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.app.Screens
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.RemindTextField
import com.example.remind.core.common.component.StepComponent
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@Composable
fun WritingMoodStep3Screen(navController: NavHostController, viewModel: WritingViewModel) {
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
    val year = LocalDate.now().year
    val month = LocalDate.now().monthValue
    val date = LocalDate.now().dayOfMonth
    val dateValue = "${year}-${month}-${date}"
    val textState = remember { mutableStateOf("") }
    val handleTextChange = { newText: String ->
        textState.value = newText
    }
    RemindTheme {
        Column (
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
            StepContainer3(modifier = Modifier.padding(top = 31.dp))
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.오늘_하루_감사한점_3가지),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(id = R.string.간단한_일기를_써도_좋아요),
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_400)
            )
            Spacer(modifier = Modifier.weight(1f))
            RemindTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                onTextChanged = handleTextChange,
                text = textState.value,
                roundedShape = 18.dp,
                hintText = stringResource(id = R.string.일기_예시),
                topPadding = 21.dp,
                bottomPadding = 86.dp
            )
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.다음),
                RoundedCorner = 12.dp,
                backgroundColor = if(textState.value != null) RemindTheme.colors.slate_100 else RemindTheme.colors.main_6,
                textColor = if(textState.value != null) RemindTheme.colors.white else RemindTheme.colors.slate_300,
                verticalPadding = 13.dp,
                onClick = {
                   viewModel.setEvent(WritingContract.Event.SendInfoButton(
                       destinationRoute = Screens.Patience.Home.SplashCheering.route,
                       currentRoute = Screens.Patience.Home.WritingMoodStep3.route,
                       localDate = dateValue,
                       detail = textState.value
                   ))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }
}
@Preview
@Composable
fun StepContainer3(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        StepComponent(text = 1, backgroundColor = RemindTheme.colors.main_6, textColor = RemindTheme.colors.slate_50)
        Spacer(modifier = modifier.width(6.dp))
        StepComponent(text = 2, backgroundColor = RemindTheme.colors.main_6, textColor = RemindTheme.colors.slate_50)
        Spacer(modifier = modifier.width(6.dp))
        StepComponent(text = 3, backgroundColor = RemindTheme.colors.main_6, textColor = RemindTheme.colors.slate_50)
    }
}