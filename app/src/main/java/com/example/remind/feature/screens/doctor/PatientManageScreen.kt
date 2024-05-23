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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.remind.R
import com.example.remind.core.common.component.BasicBackAppBar
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.graphScoreModel
import com.example.remind.data.model.response.DoctorData
import com.example.remind.data.model.response.Info
import com.example.remind.data.model.response.Rate
import com.example.remind.feature.screens.patience.moodchart.GraphComponent
import com.example.remind.feature.screens.patience.moodchart.MoodChartContract
import com.example.remind.feature.screens.patience.moodchart.ScoreList
import com.example.remind.feature.screens.patience.moodchart.ShowWeek
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphFillType
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphVisibility
import com.jaikeerthick.composable_graphs.style.LabelPosition
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PatientManageScreen(
    navController: NavHostController,
    viewModel: DoctorViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        viewModel.getPatients()
    }
    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is DoctorContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
            }
        }
    }
    val graphYaxisList = listOf(
        graphScoreModel(100, R.drawable.ic_verygood),
        graphScoreModel(75, R.drawable.ic_good),
        graphScoreModel(50, R.drawable.ic_normal),
        graphScoreModel(25, R.drawable.ic_bad),
        graphScoreModel(0, R.drawable.ic_terrible),
    )
    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = RemindTheme.colors.white)
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            BasicBackAppBar (
                modifier = Modifier.padding(top = 20.dp),
                onClick = {navController.navigateUp()},
                title = "환자 관리"
            )
            PatientProfile(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth(),
                memberData = uiState.patientInfo
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(top = 30.dp, bottom= 7.dp),
                    text = stringResource(id = R.string.약_복용률),
                    style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .clickable {
                            viewModel.setEvent(DoctorContract.Event.ClickToMedicine)
                        },
                    text = "상세보기",
                    style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.grayscale_3)
                )
            }
            if(uiState.rate != null) {
                DoctorMedicineRateContainer(
                    rate = uiState.rate
                )
            }
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                text = "약 처방 업데이트",
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 13.dp,
                onClick = {
                   viewModel.setEvent(DoctorContract.Event.ClickToUpdate)
                },
                textStyle = RemindTheme.typography.b2Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 27.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = "무드 차트 기록",
                    style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .clickable(
                            onClick = { viewModel.setEvent(DoctorContract.Event.ClickToMood) }
                        ),
                    text = "상세보기",
                    style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.grayscale_3)
                )
            }
            if(uiState.xAxisData.isNotEmpty() && uiState.yAxisData.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(300.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp),
                            color = RemindTheme.colors.grayscale_2
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.padding(end = 7.dp),
                            painter = painterResource(id = R.drawable.ic_arrow_graph_left),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier.padding(end = 7.dp),
                            text = ShowWeek(),
                            style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.text)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_graph_right),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 7.dp, end = 10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(top = 20.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            graphYaxisList.forEach { data ->
                                ScoreList(
                                    data = data,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(25.dp))
                        if(uiState.xAxisData.isNotEmpty() && uiState.yAxisData.isNotEmpty()) {
                            GraphComponent(
                                modifier = Modifier.weight(4f),
                                dateListX = uiState.xAxisData,
                                dateListY = uiState.yAxisData,
                                pointClick = {}
                            )
                        } else {
                            CircularProgressIndicator()
                        }
                    }
                }
            } else {
                CircularProgressIndicator()
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PatientProfile(
    modifier: Modifier = Modifier,
    memberData: Info,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .size(width = 80.dp, height = 80.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
            model = memberData.imageUrl,
            contentDescription = null
        )
        Column(
            modifier = modifier
                .padding(start = 8.dp),
           horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = memberData.name,
                style = RemindTheme.typography.b1Bold
            )
            Text(
                text = "만${memberData.age} ${memberData.gender}",
                style = RemindTheme.typography.h2Medium.copy(fontSize = 14.sp)
            )
        }
    }
}

@Composable
fun DoctorMedicineRateContainer(
    modifier: Modifier = Modifier,
    rate: Rate
) {
    val total = String.format("%.1f", rate.totalRate)
    val breakfast = String.format("%.1f", rate.breakfastRate)
    val lunch = String.format("%.1f", rate.lunchRate)
    val dinner = String.format("%.1f", rate.dinnerRate)
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
                    text = "${total}%",
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
                    modifier = modifier.padding(start = 4.dp ,end = 12.dp),
                    text = "$breakfast%",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.main_6)
                )
                Text(
                    text = "점심: ",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.text)
                )
                Text(
                    modifier = modifier.padding(start = 4.dp,end = 12.dp),
                    text = "$lunch%",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.main_6)
                )
                Text(
                    text = "저녁: ",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.text)
                )
                Text(
                    modifier = modifier.padding(start = 4.dp),
                    text = "$dinner%",
                    style = RemindTheme.typography.c2Medium.copy(color= RemindTheme.colors.main_6)
                )
            }
        }
    }
}

@Composable
fun DoctorGraphComponent(
    modifier: Modifier = Modifier,
    dateListX: List<String>,
    dateListY: List<Int>,
    pointClick: (String) -> Unit
){
    val xAxisData = dateListX
    val yAxisData = dateListY
    val dataModel = mutableListOf<LineData>()
    for(i in xAxisData.indices) {
        val lineData = LineData(xAxisData[i], yAxisData[i])
        dataModel.add(lineData)
    }
    Column(
        modifier = modifier.padding(top = 20.dp, bottom = 20.dp)
    ) {
        val style = LineGraphStyle(
            visibility = LineGraphVisibility(
                isXAxisLabelVisible = true,
                isYAxisLabelVisible = false,
                isCrossHairVisible = false
            ),
            colors = LineGraphColors(
                lineColor = RemindTheme.colors.main_7,
                pointColor = RemindTheme.colors.main_7,
                clickHighlightColor = RemindTheme.colors.main_6,
                fillType = LineGraphFillType.Gradient(brush = Brush.verticalGradient(listOf(
                    RemindTheme.colors.main_6.copy(alpha = 0.63f), RemindTheme.colors.white
                )))
            ),
            yAxisLabelPosition = LabelPosition.LEFT
        )
        LineGraph(
            modifier = Modifier.padding(top = 17.dp),
            data = dataModel,
            style = style,
            onPointClick = { lineData->
                pointClick(lineData.x)
            }
        )
    }
}