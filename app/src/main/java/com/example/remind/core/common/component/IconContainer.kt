package com.example.remind.core.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.FeelingScoreModel

@Composable
fun IconContainer(
    modifier: Modifier = Modifier,
    feelingScoreModel: FeelingScoreModel,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(
                        top = 18.dp,
                        start = 15.dp,
                        end = 15.dp,
                        bottom = 16.dp
                    )
                    .size(
                        width = 25.dp,
                        height = 25.dp
                    ),
                painter = painterResource(id = feelingScoreModel.imgeRes),
                contentDescription = null
            )
            Text(
                modifier = modifier.padding(bottom = 15.dp),
                text = feelingScoreModel.feeling,
                style = RemindTheme.typography.c1Bold.copy(color = RemindTheme.colors.slate_700)
            )
        }
    }
}