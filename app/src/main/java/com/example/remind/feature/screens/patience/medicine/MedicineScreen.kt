package com.example.remind.feature.screens.patience.medicine

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.StarRatingBar
import com.example.remind.core.designsystem.theme.Pretendard
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.response.Prescription
import com.example.remind.data.model.response.Rate
import com.example.remind.feature.screens.patience.medicine.component.MedicineCalendar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MedicineScreen(navController: NavHostController, viewModel: MedicineViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        viewModel.getPrescription()
        viewModel.getMedicineRate()
        viewModel.getMonthMedicine()
    }

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

    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = RemindTheme.colors.white)
                .padding(start = 20.dp, end = 20.dp)
                .verticalScroll(scrollState),
        ) {
            Text(
                modifier = Modifier.padding(top = 19.6.dp),
                text = stringResource(id = R.string.약_복용),
                style = RemindTheme.typography.h2Bold.copy(color = RemindTheme.colors.text)
            )
            Row(
                modifier = Modifier.padding(top = 34.dp),
               verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${uiState.prescription.name}님의 약 처방",
                    style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = uiState.prescription.prescriptionDate,
                    style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.text)
                )
                Text(
                    modifier = Modifier.padding(start = 6.dp),
                    text = "(${uiState.prescription.period}일분 처방)",
                    style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.grayscale_3)
                )
            }
            CurrentMedicineContainer(
                modifier = Modifier.padding(top = 7.dp, bottom = 8.dp),
                prescription = uiState.prescription
            )
            BasicButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.알림_설정),
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 12.dp,
                onClick = {
                   viewModel.setEvent(MedicineContract.Event.moveToButtonClicked(context))
                },
                textStyle = RemindTheme.typography.b2Bold
            )
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = stringResource(id = R.string.약_복용률),
                style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
            )
            MedicineRateContainer(
                modifier = Modifier.padding(top = 6.dp, bottom = 10.dp),
                rate = uiState.rate
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 66.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = RemindTheme.colors.slate_100,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CalendarHeader(modifier = Modifier.padding(top = 9.dp, bottom = 9.dp))
                    MedicineCalendar(monthData = uiState.getMonthlyMedicineResponse.data.monthlyTakingMedicineDtos)
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            modifier = Modifier.padding(end = 15.dp, bottom = 24.dp),
                            painter = painterResource(id = R.drawable.ex_info),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier.size(width = 7.dp, height = 6.dp),
            painter = painterResource(id = R.drawable.ic_arrow_graph_left),
            contentDescription = null
        )
        Text(
            modifier = modifier.padding(start = 4.dp),
            text = "2024년 5월",
            style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.text)
        )
        Image(
            modifier = modifier
                .padding(start = 4.dp)
                .size(width = 7.dp, height = 6.dp),
            painter = painterResource(id = R.drawable.ic_arrow_graph_right),
            contentDescription = null
        )
    }
}

@Composable
fun CurrentMedicineContainer(
    modifier: Modifier = Modifier,
    prescription: Prescription
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = RemindTheme.colors.slate_100,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = modifier.padding(horizontal = 19.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TimeMedicineList(
                    time = stringResource(id = R.string.아침),
                    importance = prescription.breakfastImportance.toFloat()
                )
                TimeMedicineList(
                    time = stringResource(id = R.string.점심),
                    importance = prescription.lunchImportance.toFloat()
                )
                TimeMedicineList(
                    time = stringResource(id = R.string.저녁),
                    importance = prescription.dinnerImportance.toFloat()
                )
                TimeMedicineList(
                    time = stringResource(id = R.string.비상용),
                    importance = prescription.etcImportance.toFloat()
                )
            }
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .background(
                        color = RemindTheme.colors.main_2,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(start = 14.dp, top = 3.dp, bottom = 3.dp),
                text = prescription.memo,
                style = RemindTheme.typography.c2Regular.copy(color = RemindTheme.colors.slate_700)
            )
        }
    }
}

@Composable
fun TimeMedicineList(
    modifier: Modifier = Modifier,
    time: String,
    importance: Float,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier.padding(bottom = 7.dp),
            text = time,
            style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.main_6)
        )
        StarRatingBar(
            rating = importance,
            onRatingChanged = {}
        )
    }
}

@Composable
fun MedicineRateContainer(
    modifier: Modifier = Modifier,
    rate: Rate
) {
    val total = "${String.format("%.1f", rate.totalRate)}%"
    val breakfast = "${String.format("%.1f", rate.breakfastRate)}%"
    val lunch = "${String.format("%.1f", rate.lunchRate)}%"
    val dinner = "${String.format("%.1f", rate.dinnerRate)}%"
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = RemindTheme.colors.slate_100,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = modifier.padding(vertical = 19.dp, horizontal = 20.dp),
            horizontalAlignment = Alignment.Start
        ) {
           Row {
               Text(
                   text = total,
                   style = RemindTheme.typography.h1Bold.copy(color= RemindTheme.colors.main_6)
               )
               Text(
                   modifier = modifier.padding(start = 9.dp),
                   text = "(종합 복용률)",
                   style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.text)
               )
           }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Text(
                    text = "아침: ",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.text)
                )
                Text(
                    modifier = modifier.padding(start = 4.dp, end = 12.dp),
                    text = breakfast,
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.main_6)
                )
                Text(
                    text = "점심: ",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.text)
                )
                Text(
                    modifier = modifier.padding(start = 4.dp, end = 12.dp),
                    text = lunch,
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.main_6)
                )
                Text(
                    text = "저녁: ",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.text)
                )
                Text(
                    modifier = modifier.padding(start = 4.dp, end = 12.dp),
                    text = dinner,
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.main_6)
                )
            }
        }
    }
}

