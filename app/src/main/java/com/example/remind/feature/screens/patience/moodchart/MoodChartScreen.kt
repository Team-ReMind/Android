package com.example.remind.feature.screens.patience.moodchart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicTextButton
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.graphScoreModel
import com.example.remind.data.model.response.FeelingActivity
import com.example.remind.data.repository.CalendarDataSource
import com.example.remind.feature.screens.patience.moodchart.component.FeelingPercentGraph
import com.jaikeerthick.composable_graphs.composables.line.LineGraph
import com.jaikeerthick.composable_graphs.composables.line.model.LineData
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphColors
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphFillType
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphStyle
import com.jaikeerthick.composable_graphs.composables.line.style.LineGraphVisibility
import com.jaikeerthick.composable_graphs.style.LabelPosition
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MoodChartScreen(navController: NavHostController, viewModel:MoodChartViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val graphYaxisList = listOf(
        graphScoreModel(100, R.drawable.ic_verygood),
        graphScoreModel(75, R.drawable.ic_good),
        graphScoreModel(50, R.drawable.ic_normal),
        graphScoreModel(25, R.drawable.ic_bad),
        graphScoreModel(0, R.drawable.ic_terrible),
    )
    if(isSheetOpen == true) {
        MoodChartBottomSheet(
            viewModel = viewModel,
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            }
        )
    }
    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is MoodChartContract.Effect.NavigateTo -> {
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
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(19.6.dp))
            Text(
                text = stringResource(id = R.string.무드_차트),
                style = RemindTheme.typography.h2Bold.copy(Color(0xFF303030))
            )
            Spacer(modifier = Modifier.height(22.dp))
            BasicTextButton(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = RemindTheme.colors.slate_600,
                text = "${uiState.currentSeriesDay}일째 연속으로 기록 중이에요! 파이팅:)",
                textColor = RemindTheme.colors.slate_100,
                onClick = {  },
                verticalPadding = 7.dp,
                enable = false
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.주간_차트_기록),
                style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.slate_800,)
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicButton(
                modifier = Modifier.fillMaxWidth(),
                text = if(uiState.date == "") "${showSelectDate()} 기록 확인" else "${showChangeDate(uiState.date)} 기록 확인",
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 18.dp,
                onClick = {
                   viewModel.setEvent(MoodChartContract.Event.clickToBottomsheet)
                },
                textStyle = RemindTheme.typography.b3Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
                            pointClick = {clickedDate->
                                viewModel.setEvent(MoodChartContract.Event.storeDate(clickedDate))
                            }
                        )
                    } else {
                        CircularProgressIndicator()
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = stringResource(id = R.string.기분별_활동_차트),
                style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.text)
            )
            Text(
                modifier = Modifier.padding(top = 3.dp),
                text = stringResource(id = R.string.무엇을_할_때_기분이_좋은지_확인),
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.grayscale_3)
            )
//            Box(
//                modifier = Modifier
//                    .padding(top = 8.dp)
//                    .border(
//                        width = 1.dp,
//                        color = RemindTheme.colors.grayscale_2,
//                        shape = RoundedCornerShape(12.dp)
//                    )
//            ) {
//                if(uiState.feelingTotalPerCent.isNotEmpty()) {
//                    FeelingPercentGraph(
//                        modifier = Modifier.padding(top = 12.dp, bottom = 21.dp, start = 8.dp, end = 8.dp),
//                        percentList = uiState.feelingTotalPerCent,
//                        onClick = {}
//                    )
//                } else {
//                    CircularProgressIndicator()
//                }
//
//            }
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.ex_percentage),
                contentDescription = null
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.ex_activity),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = stringResource(id = R.string.무드_차트_월별_비교)
            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 105.dp),
                painter =  painterResource(id = R.drawable.moodcontainer_example),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}

@Composable
fun GraphComponent(
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

@Composable
fun ScoreList(
    modifier: Modifier = Modifier,
    data: graphScoreModel
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(15.dp, 15.dp),
            painter = painterResource(id = data.img),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = data.score.toString(),
            style = RemindTheme.typography.c2Medium.copy(color = RemindTheme.colors.text)
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ActivityListItem(
    modifier: Modifier = Modifier,
    feelingActivity: FeelingActivity,
) {
    Box(
        modifier = modifier
            .background(color = RemindTheme.colors.main_1, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = modifier.padding(vertical = 7.dp, horizontal = 9.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(
                modifier = Modifier.size(width = 23.dp, height = 23.dp),
                model = feelingActivity.iconImage,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = feelingActivity.name,
                style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.text)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(end = 9.dp),
                text = "${feelingActivity.percent}%",
                style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.text)
            )
        }
    }
}


fun ShowWeek(): String {
    val dataSoruce = CalendarDataSource()
    val result = dataSoruce.getWeeklyGraph(LocalDate.now())
    return result
}

fun showSelectDate(): String {
    val dataSource = CalendarDataSource()
    val result = dataSource.getDayForSearch(LocalDate.now())
    return result
}
fun showChangeDate(date: String): String {
    val dataSource = CalendarDataSource()
    val result = dataSource.getDayForSearchChange(date)
    return result
}

