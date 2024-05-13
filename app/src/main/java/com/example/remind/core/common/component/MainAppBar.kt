package com.example.remind.core.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun MainAppBar(
    modifier: Modifier,
    onClick: () -> Unit

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = modifier
                .size(width = 36.dp, height = 21.dp)
        )
        Spacer(Modifier.weight(1f))
        IconButton(
            onClick = onClick,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_setting),
                contentDescription = null,
                modifier = modifier
                    .size(width = 24.dp, height = 28.dp),
                tint = RemindTheme.colors.icon
            )
        }
    }
}

@Preview
@Composable
fun MainAppBarPreview() {
    MainAppBar(modifier = Modifier, onClick = {})
}