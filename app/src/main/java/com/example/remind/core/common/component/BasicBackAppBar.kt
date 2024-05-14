package com.example.remind.core.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun BasicBackAppBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = RemindTheme.colors.white),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 20.dp, top = 13.dp, bottom = 16.dp)
                .clickable(onClick = onClick),
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            tint = RemindTheme.colors.icon
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = title,
            style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.text)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}
