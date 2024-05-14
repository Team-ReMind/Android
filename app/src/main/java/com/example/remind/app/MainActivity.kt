package com.example.remind.app

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.remind.core.designsystem.theme.RemindTheme
import com.google.firebase.Firebase
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.messaging.messaging

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
        //곧 지워질 코드
        Firebase.messaging.token.addOnCompleteListener { task ->
            if(!task.isSuccessful) {
                Log.e("tagg", "failed")
            } else {
                val token = task.result
                Log.d("taag", "$token")
            }
        }
    }
}

