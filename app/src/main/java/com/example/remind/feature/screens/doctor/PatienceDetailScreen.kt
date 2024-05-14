package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicBackAppBar
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun PatienceDetailScreen(
    navController: NavHostController,
) {
    RemindTheme {
        Column() {
            BasicBackAppBar(
                onClick = { navController.navigateUp() },
                title = stringResource(id = R.string.환자_관리)
            )

        }
    }
}

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    name: String,
    age: String,
    gender: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //예비
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = modifier
                .size(width = 71.dp, height = 95.dp)
                .padding(end = 8.dp),
            tint = RemindTheme.colors.main_1
        )
        Column {

        }
    }
}