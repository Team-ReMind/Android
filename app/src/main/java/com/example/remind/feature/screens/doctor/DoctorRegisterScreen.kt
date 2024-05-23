package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicBackAppBar
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.response.PatientDto
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DoctorRegisterScreen(
    navController: NavHostController,
    viewModel: DoctorViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is DoctorContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
            }
        }
    }
    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = RemindTheme.colors.white)
        ) {
            BasicBackAppBar (
                modifier = Modifier.padding(top = 20.dp),
                onClick = {navController.navigateUp()},
                title = stringResource(id = R.string.환자_추가하기)
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                CodeContainer(
                    modifier = Modifier,
                    code = uiState.doctorData.targetMemberCode
                )
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = stringResource(id = R.string.추가_요청),
                    style = RemindTheme.typography.b3Bold.copy(color = RemindTheme.colors.text)
                )
                LazyColumn(
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    itemsIndexed(uiState.acceptList.patientDtos) { index, item ->
                        RequestListItem(
                            data = uiState.acceptList.patientDtos.get(index),
                            onClick = {
                                viewModel.setEvent(
                                    DoctorContract.Event.acceptButtonClicked(
                                        memberId = item.memberId
                                    )
                                )
                            },
                            status = uiState.connectionStatus
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CodeContainer(
    modifier: Modifier = Modifier,
    code: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = RemindTheme.colors.main_1, shape = RoundedCornerShape(12.dp))
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(top = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.담당_환자_추가시_코드_번호를_공유하세요),
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.text)
            )
            Spacer(modifier = Modifier.padding(bottom = 18.dp))
            Text(
                text = code,
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.text),
                letterSpacing = 19.sp
            )
            Spacer(modifier = Modifier.padding(bottom = 6.79.dp))
            Box(
                modifier = Modifier
                    .padding(start = 48.dp, end = 50.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = RemindTheme.colors.slate_800)
            )
            Spacer(modifier = Modifier.padding(bottom = 29.dp))

        }
    }
}

@Composable
fun RequestListItem(
    modifier: Modifier = Modifier,
    data: PatientDto,
    onClick: () -> Unit,
    status: Boolean
) {
    Column(
        modifier = modifier
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${data.name} 환자(${data.gender})가 추가를 요청했습니다.",
                style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.text)
            )
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                modifier = Modifier.width(57.dp),
                text = if(status) "추가됨" else "수락",
                backgroundColor = if(status) RemindTheme.colors.slate_600 else RemindTheme.colors.main_6,
                RoundedCorner = 20.dp,
                textColor = RemindTheme.colors.white,
                verticalPadding = 5.dp,
                onClick = onClick,
                textStyle = RemindTheme.typography.c1Medium
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = RemindTheme.colors.grayscale_1)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    //CodeContainer(modifier = Modifier, code = "1A2BVZ")
    //DoctorRegisterScreen()
}