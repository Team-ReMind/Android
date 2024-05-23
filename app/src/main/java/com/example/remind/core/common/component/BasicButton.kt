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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    text: String,
    RoundedCorner: Dp,
    backgroundColor: Color,
    textColor: Color,
    verticalPadding: Dp,
    onClick: () -> Unit,
    textStyle: TextStyle,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(RoundedCorner),
        contentPadding = PaddingValues(vertical = verticalPadding),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
    ) {
        Text(
            text = text,
            style = textStyle
        )
    }
}
