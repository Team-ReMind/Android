package com.example.remind.feature.screens.auth.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.remind.R
import com.example.remind.core.common.component.BasicButton
import com.example.remind.core.common.component.BasicOnBoardingAppBar
import com.example.remind.core.common.component.RemindTextField
import com.example.remind.core.designsystem.theme.RemindTheme
import com.example.remind.data.model.request.OnBoardingRequest
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OnBoardingUserInfoScreen(
    navController: NavHostController,
    viewModel:OnBoardingViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect
    val textStates = remember { mutableStateMapOf<String, String>() }
    val fieldKeys = listOf("nameField", "phnumberField", "bornField", "hospitalField")
    var isCheckedMale by remember{ mutableStateOf(false) }
    var isCheckedFeMale by remember{ mutableStateOf(false) }

    LaunchedEffect(true) {
        effectFlow.collectLatest { effect ->
            when(effect) {
                is OnBoardingContract.Effect.NavigateTo -> {
                    navController.navigate(effect.destination, effect.navOptions)
                }

                else -> {}
            }
        }
    }
    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
                .background(color = RemindTheme.colors.white)
        ) {
            BasicOnBoardingAppBar(
                modifier = Modifier.fillMaxWidth(),
                weight = 0.5f,
                title = ""
            )
            Text(
                modifier = Modifier.padding(top = 35.dp),
                textAlign = TextAlign.Start,
                text = "사용자 정보 입력",
                style = RemindTheme.typography.h1Bold.copy(color = RemindTheme.colors.text)
            )
            Text(
                modifier = Modifier.padding(top = 12.dp),
                textAlign = TextAlign.Start,
                text = "원활한 서비스 이용을 위한\n사용자의 정보를 입력해주세요!",
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.grayscale_3, lineHeight = 22.sp)
            )
            Text(
                modifier = Modifier.padding(top = 34.dp),
                text= "성함",
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
            )
            RemindTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                onTextChanged = { newText -> textStates[fieldKeys[0]] = newText },
                text = textStates[fieldKeys[0]] ?: "",
                roundedShape = 8.dp,
                hintText = "사용자님의 성함을 입력해주세요.",
                topPadding = 13.dp,
                bottomPadding = 13.dp,
                maxLine = 1
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text= "성별",
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(end = 12.dp)
                        .background(
                            color = RemindTheme.colors.slate_100,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier.padding(start = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isCheckedMale,
                            colors = CheckboxDefaults.colors(
                                checkedColor = RemindTheme.colors.main_6,
                                uncheckedColor = Color(0xFF6B7280),
                                checkmarkColor = RemindTheme.colors.white
                            ),
                            onCheckedChange = { isCheckedMale = it }
                        )
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "남",
                            style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .background(
                            color = RemindTheme.colors.slate_100,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier.padding(start = 15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isCheckedFeMale,
                            colors = CheckboxDefaults.colors(
                                checkedColor = RemindTheme.colors.main_6,
                                uncheckedColor = Color(0xFF6B7280),
                                checkmarkColor = RemindTheme.colors.white
                            ),
                            onCheckedChange = { isCheckedFeMale = it }
                        )
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "여",
                            style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text= "전화번호",
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
            )
            RemindTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                onTextChanged = { newText -> textStates[fieldKeys[1]] = newText },
                text = textStates[fieldKeys[1]] ?: "",
                roundedShape = 8.dp,
                hintText = "번호를 입력헤주세요.",
                topPadding = 13.dp,
                bottomPadding = 13.dp,
                maxLine = 1
            )
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text= "출생연도",
                style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
            )
            RemindTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                onTextChanged = { newText -> textStates[fieldKeys[2]] = newText },
                text = textStates[fieldKeys[2]] ?: "",
                roundedShape = 8.dp,
                hintText = "출생연도를 입력해주세요.",
                topPadding = 13.dp,
                bottomPadding = 13.dp,
                maxLine = 1
            )
            if(uiState.selectedType == "ROLE_DOCTOR") {
                Text(
                    modifier = Modifier.padding(top = 20.dp),
                    text= "병원이름",
                    style = RemindTheme.typography.b2Medium.copy(color = RemindTheme.colors.text)
                )
                RemindTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 4.dp),
                    onTextChanged = { newText -> textStates[fieldKeys[3]] = newText },
                    text = textStates[fieldKeys[3]] ?: "",
                    roundedShape = 8.dp,
                    hintText = "소속 병원 이름을 입력해주세요.",
                    topPadding = 13.dp,
                    bottomPadding = 13.dp,
                    maxLine = 1
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                text = stringResource(id = R.string.다음),
                RoundedCorner = 12.dp,
                backgroundColor = if(textStates[fieldKeys[0]] != null) RemindTheme.colors.main_6 else RemindTheme.colors.slate_100,
                textColor = if(textStates[fieldKeys[0]] != null) RemindTheme.colors.white else RemindTheme.colors.slate_300,
                verticalPadding = 13.dp,
                onClick = {
                  viewModel.setEvent(
                      OnBoardingContract.Event.StoreUserInfoButtonClicked(
                          info = OnBoardingRequest(
                              centerName = "",
                              city = "",
                              district = "",
                              protectorPhoneNumber = "",
                              rolesType = uiState.selectedType!!,
                              fcmToken = "",
                              doctorLicenseNumber = textStates[fieldKeys[3]] ?: "",
                              birthday = textStates[fieldKeys[2]] ?: "",
                              phoneNumber = textStates[fieldKeys[1]] ?: "",
                              gender = if(isCheckedMale == true) "남" else "여",
                              name = textStates[fieldKeys[0]] ?: ""
                          )
                      )
                  )
                },
                textStyle = RemindTheme.typography.b2Bold
            )
        }
    }
}
