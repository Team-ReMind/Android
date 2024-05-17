package com.example.remind.feature.screens.patience.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.remind.R
import com.example.remind.core.designsystem.theme.RemindTheme

@Composable
fun HomeCheeringScreen() {
    RemindTheme {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_example_cheer),
            contentDescription = null
        )

    }
}

@Preview
@Composable
fun CheeringPreview() {
    HomeCheeringScreen()
}