package com.example.remind.core.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun BasicListItem(
    modifier: Modifier = Modifier,
    name: String,
    index: String,
    backgroundColor: Color,
    catiousClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .padding(start = 11.dp, end = 9.dp, top = 10.dp, bottom = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = index,
            color = RemindTheme.colors.text,
            style = RemindTheme.typography.b3Medium
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            text = name,
            color = RemindTheme.colors.text,
            style = RemindTheme.typography.b3Medium
        )
        Spacer(Modifier.weight(1f))
        Text(
            modifier = modifier
                .padding(end = 5.dp)
                .clickable (
                    onClick = catiousClicked
                ),
            text = "관리",
            color = RemindTheme.colors.main_6,
            style = RemindTheme.typography.b3Medium
        )
        Image(
            painter = painterResource(id = R.drawable.ic_graph),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ListItemPreview() {
    //BasicListItem(modifier = Modifier,"송승희", "01", backgroundColor = Color.White)
}