package com.example.remind.core.common.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.example.remind.core.designsystem.theme.RemindTheme
@Composable
fun StepComponent(
    modifier: Modifier = Modifier,
    text: Int,
    backgroundColor: Color,
    textColor: Color
) {
    Box(
        modifier = modifier
            .size(28.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(100.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = modifier,
            text = text.toString(),
            style = RemindTheme.typography.b3Bold.copy(color = textColor)
        )
    }
}

@Preview
@Composable
fun StepComponentPreview() {
    StepComponent(text = 1, backgroundColor = RemindTheme.colors.main_6, textColor = RemindTheme.colors.white)
}