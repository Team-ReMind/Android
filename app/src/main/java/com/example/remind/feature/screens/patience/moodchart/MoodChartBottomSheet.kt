package com.example.remind.feature.screens.patience.moodchart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.remind.R
import com.example.remind.core.common.component.BasicBottomSheet
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.FeelingScoreModel
import com.example.remind.data.model.response.Activities
import com.example.remind.data.model.response.DailyTakingMedicineList

val feelingList = listOf(
    FeelingScoreModel(R.drawable.ic_verygood, "정말 좋음", "VERY_GOOD", "기분이 정말 좋았어요!"),
    FeelingScoreModel(R.drawable.ic_good, "좋음", "GOOD", "기분이 좋았어요!"),
    FeelingScoreModel(R.drawable.ic_normal, "보통", "NORMAL", "기분이 보통이었어요!"),
    FeelingScoreModel(R.drawable.ic_bad, "나쁨", "BAD", "기분이 나빴어요."),
    FeelingScoreModel(R.drawable.ic_terrible, "끔찍함", "TERRIBLE", "기분이 매우 안좋았어요.")
)
fun findFeelingImage(feelingType: String): Int? {
    val feelingImg = feelingList.find { it.text == feelingType }
    return feelingImg?.imgeRes
}
fun findFeelingText(feelingType: String): String? {
    val feelingText = feelingList.find { it.feeling == feelingType }
    return feelingText?.feeling
}
fun findFeelingMent(feelingType: String): String? {
    val feelingText = feelingList.find { it.feeling == feelingType }
    return feelingText?.description
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodChartBottomSheet (
    modifier: Modifier = Modifier,
    viewModel: MoodChartViewModel,
    sheetState : SheetState = rememberModalBottomSheetState(),
    onDismissRequest: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    BasicBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "날짜날짜"
                )
                Spacer(modifier = modifier.weight(1f))
                IconButton(onClick = onDismissRequest) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null
                    )
                }
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            color = RemindTheme.colors.main_1,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = modifier.padding(vertical = 5.dp)
                    ) {
                        Image(
                            modifier = modifier.padding(end = 4.dp),
                            painter = painterResource(id = findFeelingImage(uiState.dailyMood.feelingType) ?: R.drawable.ic_verygood),
                            contentDescription = null
                        )
                        Text(
                            text = findFeelingText(uiState.dailyMood.feelingType) ?: "",
                            style = RemindTheme.typography.h2Medium.copy(color = RemindTheme.colors.slate_800)
                        )
                    }
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(id = R.string.활동),
                        style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.slate_600)
                    )
                    if(uiState.dailyMood.activities.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            itemsIndexed(uiState.dailyMood.activities) {index, item ->
                                activityListItem(item = item)
                            }
                        }
                    }else {
                        CircularProgressIndicator()
                    }
                    Text(
                        modifier = modifier.padding(top = 16.dp),
                        text = stringResource(id = R.string.활동),
                        style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.slate_600)
                    )
                    Box(
                        modifier = modifier
                            .border(width = 1.dp, color = RemindTheme.colors.grayscale_1, shape = RoundedCornerShape(8.dp))
                    ) {
                        if(uiState.dailyMedicine.isNotEmpty()) {
                            LazyColumn(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(12.dp)
                            ) {
                                itemsIndexed(uiState.dailyMedicine) {index, item ->
                                    MedicineInfo(content = item)
                                }
                            }
                        } else {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun activityListItem(
    modifier: Modifier = Modifier,
    item: Activities
) {
    Box(
        modifier = modifier
            .border(width = 1.dp, color = RemindTheme.colors.grayscale_1, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 12.dp, bottom = 12.dp, end = 50.dp),
            verticalArrangement = Arrangement.Center
        ) {
            GlideImage(
                modifier = modifier.size(width = 29.dp, height = 45.dp),
                model = item.iconImg,
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 2.5.dp, horizontal = 20.dp)
                    .background(
                        color = RemindTheme.colors.main_2,
                        shape = RoundedCornerShape(18.dp)
                    ),
                text = item.name,
                style = RemindTheme.typography.c2Medium.copy(color = RemindTheme.colors.slate_600)
            )
        }
        Column {
            Row(
                modifier = modifier.padding(bottom = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image (
                    modifier = Modifier
                        .size(width = 15.dp, height = 21.dp)
                        .padding(end = 3.dp),
                    painter = painterResource(id = findFeelingImage(item.feelingType) ?: R.drawable.ic_verygood),
                    contentDescription = null
                )
                Text (
                    text = findFeelingMent(item.feelingType) ?: "",
                    style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.slate_600)
                )
            }
            Text(
                text = item.detail,
                style = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.slate_500)
            )
        }
    }
}

@Composable
fun MedicineInfo(
    modifier:Modifier = Modifier,
    content: DailyTakingMedicineList
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = content.takingTime?.let { timeForamt(it) } ?: "",
            style = RemindTheme.typography.b3Bold.copy(color = RemindTheme.colors.text)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = modifier.padding(end = 6.dp),
                painter = if(content.isTaking == true) painterResource(id = R.drawable.ic_taking) else painterResource(id = R.drawable.ic_nottaking),
                contentDescription = null
            )
            if(content.isTaking == true) {
                Text(
                    text = stringResource(id = R.string.복용),
                    style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.main_6)
                )
            } else {
                Text(
                    text = stringResource(id = R.string.미복용),
                    style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.sub_4)
                )
            }
            if(content.notTakingReason != null) {
                Text(
                    modifier = modifier
                        .padding(horizontal = 8.dp, vertical = 2.5.dp),
                    text = "사유: ${content.notTakingReason}",
                    style = RemindTheme.typography.c3Bold.copy(color = RemindTheme.colors.slate_500)
                )
            }
        }
    }
}

fun timeForamt(takingTime: String): String {
    when(takingTime) {
        "BREAKFAST" -> return "아침"
        "LUNCH" -> return "점심"
        "DINNER" -> return "저녁"
        else -> return "비상용"
    }
}