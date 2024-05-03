package com.example.remind.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object RemindTheme {
    val colors: RemindColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

    val typography: RemindTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current
}