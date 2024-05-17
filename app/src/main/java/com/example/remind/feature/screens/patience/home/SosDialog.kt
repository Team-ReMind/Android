package com.example.remind.feature.screens.patience.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun SosDialog (
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onClickToCall: () -> Unit,
    showDialog: MutableState<Boolean>
) {

}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = RemindTheme.colors.white,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.도움),
            style = RemindTheme.typography.b1Bold.copy(color = RemindTheme.colors.text)
        )
        Icon(
            modifier = modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 11.dp)
                .clickable(onClick = onDismissClick),
            painter = painterResource(id = R.drawable.ic_close),
            tint = RemindTheme.colors.icon,
            contentDescription = null
        )
        Column(
            modifier = modifier
                .align(Alignment.Center)
                .padding(top = 65.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.도움이_필요하신가요),
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.black)
            )
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.자살예방_상담_전화_연결하기),
                style = RemindTheme.typography.b2Bold.copy(color = RemindTheme.colors.black)
            )
            Image(
                modifier = modifier.padding(
                    top = 15.dp,
                    bottom = 36.dp
                ),
                painter = painterResource(id = R.drawable.ic_phone_fill),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun SosPreview() {
    Content(onDismissClick = {})
}
