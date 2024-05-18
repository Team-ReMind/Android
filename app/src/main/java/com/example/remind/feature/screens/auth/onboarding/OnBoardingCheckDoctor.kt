package com.example.remind.feature.screens.auth.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicOnBoardingAppBar
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun OnBoardingCheckDoctorScreen() {
    RemindTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BasicOnBoardingAppBar(
                modifier = Modifier.fillMaxWidth(),
                weight = 0.7f,
                title = stringResource(id = R.string.의사_면허_인증)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(id = R.string.정신질환_환자_사례관리),
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.grayscale_3)
            )
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                text = stringResource(id = R.string.인증_신청),
                RoundedCorner = 12.dp,
                backgroundColor = RemindTheme.colors.main_4,
                textColor = RemindTheme.colors.white,
                verticalPadding = 13.dp,
                onClick = { /*TODO*/ },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }

}