package com.example.remind.feature.screens.patience

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicTextButton
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun MoodChartScreen() {
    val scrollState = rememberScrollState()
    RemindTheme {
        Column(
            modifier = Modifier
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
                text = "17일째 연속으로 기록 중이에요! 파이팅:)",
                textColor = RemindTheme.colors.slate_100,
                onClick = {  },
                verticalPadding = 6.dp,
                enable = false
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.주간_차트_기록),
                style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.slate_800,)
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicButton(
                text = "기록 확인",
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 18.dp,
                onClick = {  },
                textStyle = RemindTheme.typography.b3Bold
            )
        }
    }
}


@Preview
@Composable
fun ChartPreview() {

}
