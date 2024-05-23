package com.example.remind.feature.screens.doctor

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicBackAppBar
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.graphScoreModel
import com.example.remind.feature.screens.patience.moodchart.GraphComponent
import com.example.remind.feature.screens.patience.moodchart.ScoreList
import com.example.remind.feature.screens.patience.moodchart.ShowWeek
import com.example.remind.feature.screens.patience.moodchart.component.FeelingPercentGraph
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@Composable
fun DoctorMoodChaartScreen(
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
    val year = LocalDate.now().year
    val month = LocalDate.now().monthValue
    val date = LocalDate.now().dayOfMonth
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
                title = "무드 차트 상세"
            )
            Text(
                text = "주간 차트 기록",
                style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.text)
            )
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                text = "${year}.${month}.${date} 기록 확인",
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 18.dp,
                onClick = {
                  viewModel.setEvent(DoctorContract.Event.ClickToBottomSheet)
                },
                textStyle = RemindTheme.typography.b3Bold
            )
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