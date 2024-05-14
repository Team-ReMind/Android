package com.example.remind.feature.screens.doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicBackAppBar
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun DoctorRegisterScreen(
    navController: NavHostController,
) {
    RemindTheme {
        Column(
        ) {
            BasicBackAppBar (
                modifier = Modifier,
                onClick = {navController.navigateUp()},
                title = stringResource(id = R.string.환자_추가하기)
            )
            Column(
                modifier = Modifier
                    .padding(start = 21.dp, end = 19.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                CodeContainer(
                    modifier = Modifier,
                    code = "1A2B2V"
                )
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = stringResource(id = R.string.추가_요청),
                    style = RemindTheme.typography.b3Bold.copy(color = RemindTheme.colors.text)
                )
            }
        }
    }
}

@Composable
fun CodeContainer(
    modifier: Modifier = Modifier,
    code: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = RemindTheme.colors.main_1)
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(top = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.담당_환자_추가시_코드_번호를_공유하세요),
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.text)
            )
            Spacer(modifier = Modifier.padding(bottom = 18.dp))
            Text(
                text = code,
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.text),
                letterSpacing = 19.sp
            )
            Spacer(modifier = Modifier.padding(bottom = 6.79.dp))
            Box(
                modifier = Modifier
                    .padding(start = 48.dp, end = 50.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = RemindTheme.colors.slate_800)
            )
            Spacer(modifier = Modifier.padding(bottom = 29.dp))
        }
    }
}

@Composable
fun RequestListItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = modifier
                .padding(start = 4.dp, end = 1.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.text)
            )
            BasicButton(
                text = stringResource(id = R.string.수락),
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 18.dp,
                onClick = onClick
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = RemindTheme.colors.grayscale_1)
        )
    }
}

@Composable
fun RequestListItem(
    modifier: Modifier = Modifier,
    name: String,
    gender: String,
    birthYear: String,
    onClick: () -> Unit
) {
    Column (

    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$name 환자(${gender}, ${birthYear}년생)가 추가를 요청했습니다.",
                style = RemindTheme.typography.c1Medium.copy(color = RemindTheme.colors.text)
            )
            Spacer(modifier = modifier.weight(1f))
            BasicButton(
                text = stringResource(id = R.string.수락),
                backgroundColor = RemindTheme.colors.main_6,
                textColor = RemindTheme.colors.white,
                verticalPadding = 5.dp,
                onClick = onClick
            )
        }
        Box(
            modifier = modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = RemindTheme.colors.grayscale_1)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    //CodeContainer(modifier = Modifier, code = "1A2BVZ")
    //DoctorRegisterScreen()
}