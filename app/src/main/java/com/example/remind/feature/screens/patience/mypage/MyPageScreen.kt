package com.example.remind.feature.screens.patience.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme
@Preview(showBackground = true)
@Composable
fun MyPageScreen(){
    val scrollState = rememberScrollState()
    val imageIndex = remember { mutableStateOf(0) }
    RemindTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 19.6.dp, start = 24.dp)
                    .clickable(onClick = { imageIndex.value = (imageIndex.value + 1) % 2 }),
                text = "마이페이지",
                style = RemindTheme.typography.h2Bold.copy(color = RemindTheme.colors.text)
            )
           if(imageIndex.value == 0) {
               Image(
                   modifier = Modifier.fillMaxWidth(),
                   contentScale = ContentScale.FillWidth,
                   painter = painterResource(id = R.drawable.ex_mypage),
                   contentDescription = null
               )
           }
            else {
               Image(
                   modifier = Modifier.fillMaxWidth(),
                   contentScale = ContentScale.FillWidth,
                   painter = painterResource(id = R.drawable.ex_mypage2),
                   contentDescription = null
               )
           }
        }
    }
}