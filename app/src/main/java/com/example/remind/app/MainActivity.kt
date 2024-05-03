package com.example.remind.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.remind.core.designsystem.theme.RemindTheme
import com.google.firebase.Firebase
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.messaging.messaging

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            RemindTheme {
               RemindNavHost()
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

