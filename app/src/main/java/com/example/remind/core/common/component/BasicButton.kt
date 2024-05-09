package com.example.remind.core.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color,
    textColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = verticalPadding, horizontal = horizontalPadding),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Text(
            text = text,
            style = RemindTheme.typography.c1Medium
        )
    }
}

@Composable
@Preview
fun BasicButtonPreview() {
    BasicButton(
        text = "삭제",
        backgroundColor = RemindTheme.colors.main_6,
        onClick = {},
        verticalPadding = 5.dp,
        horizontalPadding = 18.dp,
        textColor = RemindTheme.colors.text
    )
}