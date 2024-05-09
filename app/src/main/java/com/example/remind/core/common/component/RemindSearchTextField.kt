package com.example.remind.core.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme


@Composable
fun RemindSearchTextField(
    modifier: Modifier = Modifier,
    hintText: String,
    onValueChange: (String) -> Unit,
    text: String
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = RemindTheme.colors.grayscale_1,
                shape =RoundedCornerShape(20.dp)
            ),
        textStyle = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.text),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(end = 10.dp)
            ) {
                if(text.isEmpty()) {
                    Text(
                        text = hintText,
                        style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.slate_300),
                        modifier = Modifier.padding(start = 14.dp, bottom = 5.dp, top = 5.dp)
                    )
                }
                innerTextField()
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 12.dp, height = 12.dp),
                    tint = RemindTheme.colors.text
                )
            }
        }
    )
}

@Preview
@Composable
fun TextFieldPreview() {
    RemindSearchTextField(hintText = "검색", onValueChange = {}, text = "")
}