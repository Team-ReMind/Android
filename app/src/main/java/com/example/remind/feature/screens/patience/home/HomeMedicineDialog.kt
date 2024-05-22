package com.example.remind.feature.screens.patience.home

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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicDialog
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun HomeMedicineDialog (
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit,
    showDialog:Boolean,
    viewModel: HomeViewModel,
) {
    BasicDialog(
        popupContent = {
            ContentMedicineDialog(
                onDismissClick = onDismissClick,
                onConfirmClick = onConfirmClick,
                viewModel = viewModel)
        },
        showDialog = showDialog
    )
}


@Composable
fun ContentMedicineDialog(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit,
    viewModel: HomeViewModel
) {
    //val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val reasonList = listOf(
        stringResource(id = R.string.까먹었어요),
        stringResource(id = R.string.스케줄_관리를_해야해요),
        stringResource(id = R.string.다른_약물을_복용했어요),
        stringResource(id = R.string.부작용이_의심돼요)
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = RemindTheme.colors.white,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 9.13.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.약_미복용_사유),
            style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.text)
        )

        Icon(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.83.dp, end = 11.dp)
                .clickable(onClick = onDismissClick),
            painter = painterResource(id = R.drawable.ic_close),
            tint = RemindTheme.colors.icon,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 65.dp, start = 15.dp, end = 15.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                for(i in 0..1) {
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .padding(end = 3.dp)
                            .background(
                                color = if (uiState.notTakingReason == reasonList.get(i)) RemindTheme.colors.main_6 else RemindTheme.colors.slate_100,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(end = 3.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    viewModel.setEvent(
                                        HomeContract.Event.setNotTakingReason(reasonList.get(i))
                                    )
                                }
                            )
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(vertical = 7.dp),
                            text = reasonList.get(i),
                            style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.slate_700)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row {
                for(i in 2..3) {
                    Box(
                        modifier = modifier
                            .weight(1f)
                            .padding(end = 3.dp)
                            .background(
                                color = if (uiState.notTakingReason == reasonList.get(i)) RemindTheme.colors.main_6 else RemindTheme.colors.slate_100,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(end = 3.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    viewModel.setEvent(
                                        HomeContract.Event.setNotTakingReason(reasonList.get(i))
                                    )
                                }
                            )
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(vertical = 7.dp),
                            text = reasonList.get(i),
                            style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.slate_700)
                        )
                    }
                }
            }
            BasicButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp, bottom = 16.dp),
                text = stringResource(id = R.string.완료),
                RoundedCorner = 12.dp,
                backgroundColor = if(uiState.notTakingReason != null) RemindTheme.colors.main_6 else RemindTheme.colors.slate_100,
                textColor = if(uiState.notTakingReason != null) RemindTheme.colors.white else RemindTheme.colors.slate_300,
                verticalPadding = 13.dp,
                onClick = onConfirmClick,
                textStyle = RemindTheme.typography.b2Bold
            )
        }

    }
}