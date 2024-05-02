package com.example.remind.core.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindColors
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.response.ListData

@Composable
fun BasicListItem(name: String, index: String) {
    Row(
        modifier = Modifier
            .background(Color.White)
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
            text = "관리",
            color = Color.Black,
            style = RemindTheme.typography.b3Medium
        )
        Image(
            painter = painterResource(id = R.drawable.ic_listitem),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ListItemPreview() {
    BasicListItem("송승희", "01")
}