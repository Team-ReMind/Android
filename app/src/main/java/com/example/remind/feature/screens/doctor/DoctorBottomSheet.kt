package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun DoctorBottomSheet(navController: NavHostController,) {
    RemindTheme {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ex_doctor_bottomsheet),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
    }

}