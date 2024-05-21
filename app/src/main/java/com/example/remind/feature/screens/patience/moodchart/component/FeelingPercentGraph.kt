package com.example.remind.feature.screens.patience.moodchart.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.FeelingScoreModel
import com.example.remind.data.model.response.PercentList

@Composable
fun FeelingPercentGraph(
    modifier: Modifier = Modifier,
    percentList: List<PercentList>,
    onClick: () -> Unit
) {
    val convertPercentList:List<Float> = percentList.map {
        (it.percent.toInt())/100.0f
    }
    val feelingList = listOf(
        FeelingScoreModel(R.drawable.ic_verygood, "정말 좋음", "VERY_GOOD"),
        FeelingScoreModel(R.drawable.ic_good, "좋음", "GOOD"),
        FeelingScoreModel(R.drawable.ic_normal, "보통", "NORMAL"),
        FeelingScoreModel(R.drawable.ic_bad, "나쁨", "BAD"),
        FeelingScoreModel(R.drawable.ic_terrible, "끔찍함", "TERRIBLE")
    )
    fun findFeelingImage(feelingType: String): Int? {
        val feelingImg = feelingList.find { it.text == feelingType }
        return feelingImg?.imgeRes
    }
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .weight(convertPercentList.get(0))
                    .background(color = RemindTheme.colors.main_6, shape = RoundedCornerShape(4.dp))
                    .clickable(
                        onClick = onClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(vertical = 7.dp)
                        .size(width = 15.dp, height = 15.dp),
                    painter = painterResource(id = findFeelingImage(percentList.get(0).feelingType)!!),
                    contentDescription = null
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .weight(convertPercentList.get(1))
                    .background(color = RemindTheme.colors.grayscale_3)
                    .clickable(
                        onClick = onClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .size(width = 15.dp, height = 15.dp),
                    painter = painterResource(id = findFeelingImage(percentList.get(1).feelingType)!!),
                    contentDescription = null
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .weight(convertPercentList.get(2))
                    .background(color = RemindTheme.colors.grayscale_2)
                    .clickable (
                        onClick = onClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .size(width = 15.dp, height = 15.dp),
                    painter = painterResource(id = findFeelingImage(percentList.get(2).feelingType)!!),
                    contentDescription = null
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(convertPercentList.get(3))
                .background(color = RemindTheme.colors.grayscale_1)
                .clickable(
                    onClick = onClick
            ),
        contentAlignment = Alignment.Center
        ) {
        Image(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .size(width = 15.dp, height = 15.dp),
            painter = painterResource(id = findFeelingImage(percentList.get(3).feelingType)!!),
            contentDescription = null
        )
    }
        Box(
            modifier = Modifier
                .weight(convertPercentList.get(4))
                .background(color = RemindTheme.colors.sub_gray)
                .clickable(
                    onClick = onClick
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .size(width = 15.dp, height = 15.dp),
                painter = painterResource(id = findFeelingImage(percentList.get(4).feelingType)!!),
                contentDescription = null
            )
        }
    }
}