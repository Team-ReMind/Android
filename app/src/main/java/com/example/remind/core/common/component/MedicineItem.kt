package com.example.remind.core.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun MedicineItem(
    modifier: Modifier = Modifier,
    time: String,
    score: Float,
    doseClick: () -> Unit,
    unadministeredClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = RemindTheme.colors.slate_50, shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                modifier = modifier.padding(start = 12.dp, top = 7.dp),
                text = time,
                style = RemindTheme.typography.b3Bold.copy(color = RemindTheme.colors.slate_600)
            )
            Row(
                modifier = modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 2.dp,
                    bottom = 4.dp
                )
            ) {
                Text(
                    text = stringResource(id = R.string.중요도),
                    style = RemindTheme.typography.c3Medium.copy(color = RemindTheme.colors.slate_400)
                )
                Spacer(modifier = modifier.width(6.dp))
                StarRatingBar(
                    rating = score,
                    onRatingChanged = {}
                )
            }
            Spacer(modifier = modifier.height(4.dp))
            Row() {
                //시간이 정해져있을 경우 바꿔지도록 하기
                Text(
                    modifier = modifier
                        .weight(0.5f)
                        .background(
                            color = RemindTheme.colors.main_6,
                            shape = RoundedCornerShape(bottomStart = 12.dp)
                        )
                        .clickable { doseClick },
                    text = stringResource(id = R.string.복용),
                    textAlign= TextAlign.Center,
                    style = RemindTheme.typography.c1Bold.copy(
                        color = RemindTheme.colors.white,
                        lineHeight = 20.sp
                    )
                )
                Spacer(modifier = modifier.width(2.dp))
                Text(
                    modifier = modifier
                        .weight(0.5f)
                        .background(
                            color = RemindTheme.colors.main_5,
                            shape = RoundedCornerShape(bottomEnd = 12.dp)
                        )
                        .clickable { unadministeredClick },
                    text = stringResource(id = R.string.미복용),
                    textAlign= TextAlign.Center,
                    style = RemindTheme.typography.c1Bold.copy(
                        color = RemindTheme.colors.white,
                        lineHeight = 20.sp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun ItemPreview() {
    MedicineItem(time = "아침", score= 2.0f, doseClick = {}, unadministeredClick = {})
}