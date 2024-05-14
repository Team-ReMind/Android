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
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        contentPadding = PaddingValues(vertical = verticalPadding),
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
        verticalPadding = 0.dp,
        textColor = RemindTheme.colors.text
    )
}