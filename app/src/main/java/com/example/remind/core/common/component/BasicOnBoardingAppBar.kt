package com.example.remind.core.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun BasicOnBoardingAppBar(
    modifier : Modifier = Modifier,
    weight: Float,
    title: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = modifier.padding(top = 20.dp, bottom = 16.dp),
            textAlign = TextAlign.Center,
            text = title,
            style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.text)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = modifier
                    .weight(weight)
                    .height(4.dp)
                    .padding(start = 0.dp)
                    .clip(shape = RoundedCornerShape(23.dp))
                    .background(color = RemindTheme.colors.main_6)
            )
            Spacer(modifier = modifier.weight(0.7f))
        }
    }
}