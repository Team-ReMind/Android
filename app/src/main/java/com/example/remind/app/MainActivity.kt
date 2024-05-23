package com.example.remind.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.remind.core.designsystem.theme.RemindTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemindTheme {
               Surface(
                   modifier = Modifier.fillMaxSize(),
                   color = RemindTheme.colors.white
               ) {
                   RemindNavHost()
               }
            }
        }
    }
}

