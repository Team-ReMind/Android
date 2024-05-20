package com.example.remind.core.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun RemindTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    text: String,
    roundedShape: Dp,
    hintText: String,
    topPadding: Dp,
    bottomPadding: Dp
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()
    val hintTextVisible by remember {
        derivedStateOf { text.isEmpty() && !isFocused.value }
    }
    BasicTextField(
        value = text,
        maxLines = 1,
        onValueChange = onTextChanged,
        interactionSource = interactionSource,
        textStyle = RemindTheme.typography.b3Regular.copy(color = RemindTheme.colors.text),
        modifier = modifier
            .fillMaxWidth()
            .background(color = RemindTheme.colors.slate_100, shape = RoundedCornerShape(roundedShape))
            .run {
                if (isFocused.value) {
                    border(
                        width = 2.dp,
                        shape = RoundedCornerShape(roundedShape),
                        color = RemindTheme.colors.main_5
                    )
                } else {
                    this
                }
            },
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = topPadding, bottom = bottomPadding, start= 12.dp)
            ) {
                if(hintTextVisible) {
                    Text(
                        text = hintText,
                        style = RemindTheme.typography.c1Regular.copy(color = RemindTheme.colors.slate_400)
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = false)
@Composable
fun BasicTextFieldPreview() {

}