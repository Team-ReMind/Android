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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun RemindTextField(
    modifier: Modifier = Modifier,
    onTextChanged: (String) -> Unit,
    text: String,
    roundedShape: Dp,
    hintText: String,
    topPadding: Dp,
    bottomPadding: Dp,
    maxLine: Int? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()
    val hintTextVisible by remember {
        derivedStateOf { text.isEmpty() }
    }
    BasicTextField(
        value = text,
        maxLines = maxLine ?: Int.MAX_VALUE,
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
                    .padding(top = topPadding, bottom = bottomPadding, start = 12.dp)
            ) {
                if(hintTextVisible && text.isEmpty()) {
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
    val textState = remember { mutableStateOf("") }
    val handleTextChange = { newText: String ->
        textState.value = newText
    }
    RemindTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        onTextChanged = handleTextChange,
        text = textState.value,
        roundedShape = 8.dp,
        hintText = stringResource(id = R.string.번호를_입력해주세요),
        topPadding = 12.dp,
        bottomPadding = 50.dp
    )
}