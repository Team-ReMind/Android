package com.example.remind

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.remind.ui.theme.ReMindTheme
import com.google.firebase.Firebase
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.messaging.ktx.messaging
import com.google.firebase.messaging.messaging

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReMindTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("리마인드 입니당")
                }
            }
        }
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


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReMindTheme {
        Greeting("Android")
    }
}