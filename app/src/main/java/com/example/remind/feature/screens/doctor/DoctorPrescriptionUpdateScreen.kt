package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicBackAppBar
import com.example.remind.core.designsystem.theme.RemindTheme
@Composable
fun DoctorPrescriptionUpdateScreen(
    navController: NavHostController,
) {
    RemindTheme {
        Column {
            BasicBackAppBar (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                onClick = {navController.navigateUp()},
                title = "약 처방 업데이트"
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ex_updatemedi),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UpdatePreview() {
    RemindTheme {
        Column {
            BasicBackAppBar (
                modifier = Modifier
                    .padding(horizontal = 20.dp),
                onClick = {},
                title = "약 처방 업데이트"
            )
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ex_updatemedi),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
        }
    }
}