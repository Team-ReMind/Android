package com.example.remind.feature.screens.patience.medicine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AlarmSettingScreen(
    navController: NavHostController, viewModel: MedicineViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val scrollState = rememberScrollState()
    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is MedicineContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destinaton, effect.navOptions)
                }
                else-> {
                }
            }
        }
    }
    if(uiState.alarmDialogState) {
        AlarmDialog(
            onDismissClick = { viewModel.setEvent(MedicineContract.Event.dismissDialog) },
            showDialog = uiState.alarmDialogState
        )
    }
    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = RemindTheme.colors.white)
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, top = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrowleft),
                        contentDescription = null
                    )
                }
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    text = "약 복용 알림 설정",
                    style = RemindTheme.typography.h2Bold.copy(color = RemindTheme.colors.text)
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "주기 설정",
                    style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
                )
                Spacer(modifier = Modifier.weight(1f))
                BasicButton(
                    modifier = Modifier.size(width = 79.dp, height = 28.dp),
                    text = "알림 추가",
                    RoundedCorner = 20.dp,
                    backgroundColor = RemindTheme.colors.main_6,
                    textColor = RemindTheme.colors.white,
                    verticalPadding = 5.dp,
                    onClick = {
                             viewModel.setEvent(MedicineContract.Event.popDialog)
                         },
                    textStyle = RemindTheme.typography.c1Medium
                )

            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "알림을 통해 복용 시간을 놓치지 말아요!",
                style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.grayscale_3)
            )
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ex_alarm),
                contentDescription = null
            )
        }
    }
}

@Composable
fun listItem(

) {

}