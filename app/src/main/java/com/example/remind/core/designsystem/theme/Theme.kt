package com.example.remind.core.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

fun colors() = RemindColors()
fun fonts() = RemindTypography()

@Composable
fun RemindTheme (
    colors: RemindColors = colors(),
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    SideEffect {
        if(!view.isInEditMode) {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.grayscale_3.toArgb()
        }
    }
    CompositionLocalProvider (
        LocalColors provides colors,
        LocalTypography provides fonts()


    ) {
        content()
    }
}