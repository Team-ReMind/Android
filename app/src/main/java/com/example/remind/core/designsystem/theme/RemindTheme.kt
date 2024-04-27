package com.example.remind.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object RemindTheme {
    val colors: Colors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current


}