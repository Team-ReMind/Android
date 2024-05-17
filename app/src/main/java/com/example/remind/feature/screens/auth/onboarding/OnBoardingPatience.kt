package com.example.remind.feature.screens.auth.onboarding

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicOnBoardingAppBar
import com.example.remind.core.designsystem.theme.Pretendard
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.request.OnBoardingRequest
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingPatienceScreen(
    navController: NavHostController
) {
    val viewModel: OnBoardingViewModel = hiltViewModel()
    val effectFlow = viewModel.effect
    val context = LocalContext.current
    var isChecked by remember{ mutableStateOf(false) }

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is OnBoardingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }
                is OnBoardingContract.Effect.Toastmessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    RemindTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BasicOnBoardingAppBar(
                modifier = Modifier.fillMaxWidth(),
                weight = 0.5f,
                title = stringResource(id = R.string.사용자_정보)
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 35.dp, end = 20.dp),
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.보호자_정보_입력),
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text)
            )
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 12.dp, end = 20.dp),
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.긴급_시_연락할_수_있는),
                style = TextStyle(
                    fontFamily = Pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.82.sp,
                    lineHeight = 32.sp,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                ),
            )
            Spacer(modifier = Modifier.height(56.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                painter = painterResource(id = R.drawable.ic_example1),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(12.dp))
            CheckReading(
                modifier = Modifier.fillMaxWidth(),
                checked = isChecked,
                onCheckedChange = {isChecked = it}
            )
            Spacer(modifier = Modifier.weight(1f))
            typeButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                backgroundColor = RemindTheme.colors.main_6,
                text = stringResource(id = R.string.다음),
                onClick = {
                   viewModel.setEvent(OnBoardingContract.Event.NextButtonFinal(
                       OnBoardingRequest(
                           centerName = "",
                           city = "",
                           district = "",
                           protectorPhoneNumber = "01088644622",
                           rolesType = "ROLE_PATIENT",
                           fcmToken = ""
                       )
                   ))
                },
                textColor = RemindTheme.colors.white,
                enable = true
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun CheckReading(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
       horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            colors = CheckboxDefaults.colors(
                checkedColor = RemindTheme.colors.main_6,
                uncheckedColor = Color(0xFF6B7280),
                checkmarkColor = RemindTheme.colors.white
            ),
            onCheckedChange = onCheckedChange
        )
        Spacer(modifier = Modifier.width(9.dp))
        Column() {
            Text(
                text = stringResource(id = R.string.긴급_상황_발생_시_보호자에게_연락),
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.black)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.긴급_상황_발생시_담당자가_보호자에게),
                style = RemindTheme.typography.c1Regular.copy(color = RemindTheme.colors.grayscale_3, lineHeight = 6.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Text(
                text = stringResource(id = R.string.긴급_상황_발생_시_보호자에게_연락),
                style = RemindTheme.typography.b3Medium.copy(color = RemindTheme.colors.black)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.긴급_상황_발생시_담당자가_보호자에게),
                style = RemindTheme.typography.c1Regular.copy(color = RemindTheme.colors.grayscale_3, lineHeight = 6.sp)
            )
        }
    }
}