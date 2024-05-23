package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicBackAppBar
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.response.Prescription
import com.example.remind.feature.screens.patience.medicine.CalendarHeader
import com.example.remind.feature.screens.patience.medicine.TimeMedicineList
import com.example.remind.feature.screens.patience.medicine.component.MedicineCalendar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DoctorMedicineManage(
    navController: NavHostController,
    viewModel: DoctorViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val scrollState = rememberScrollState()
    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is DoctorContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
            }
        }
    }
    val patientInfo = uiState.acceptList.patientDtos.find { it.memberId == uiState.memberId }
    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = RemindTheme.colors.white)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            BasicBackAppBar (
                modifier = Modifier,
                onClick = {navController.navigateUp()},
                title = "환자 관리"
            )
            Row(
                modifier = Modifier.padding(top = 34.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${patientInfo?.name}님의 약 처방",
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
            PatientMedicineContainer(
                modifier = Modifier.padding(top = 8.dp),
                prescription = uiState.prescription
            )
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "약 처방 업데이트",
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 13.dp,
                onClick = {},
                textStyle = RemindTheme.typography.b2Bold
            )
            Text(
                modifier = Modifier.padding(top = 30.dp, bottom = 6.dp),
                text = stringResource(id = R.string.약_복용률),
                style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
            )
            if(uiState.rate != null) {
                DoctorMedicineRateContainer(
                    modifier = Modifier.padding(bottom = 8.dp),
                    rate = uiState.rate
                )
            }
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

@Composable
fun PatientMedicineContainer(
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